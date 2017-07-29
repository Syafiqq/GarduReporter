package app.freelancer.syafiqq.gardureporter.model.dao;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 07 July 2017, 8:50 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.controller.SplashScreen;
import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import app.freelancer.syafiqq.gardureporter.model.service.AuthService;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class TokenDao
{
    private static final String API_AUTH_CHECK = "check";

    public static void checkTokenExistence(@NotNull final Context context, @NotNull TokenDao.TokenExistenceListener listener)
    {
        Timber.d("checkTokenExistence");

        @NotNull final SharedPreferences settings = context.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, Context.MODE_PRIVATE);
        String token = settings.getString(context.getResources().getString(R.string.shared_preferences_authentication_token), null);
        String refresh = settings.getString(context.getResources().getString(R.string.shared_preferences_authentication_token), null);
        if(token != null)
        {
            listener.tokenExists(new TokenOrm(token, refresh));
        }
        else
        {
            listener.tokenNotExists(State.NEED_AUTH);
        }
    }

    public static void checkValidity(@NotNull final Context context, final TokenOrm token, @NotNull final TokenDao.TokenValidityListener listener)
    {
        Timber.d("checkValidity");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.getOurInstance().getNetworking().getDomain())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Setting.Networking.getReservedClient(context))
                .build();
        @NotNull final AuthService authService = retrofit.create(AuthService.class);
        authService.tokenCheck(token).enqueue(new Callback<ResponseBody>()
        {
            @Override public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                String message = "";
                boolean success = false;
                @Nullable ResponseBody body = response.body();
                if(body != null)
                {
                    try
                    {
                        JSONObject res = new JSONObject(body.string());
                        JSONObject data = res.optJSONObject("data");
                        if(data != null)
                        {
                            success = data.optInt("status") > 1;
                            message = data.optJSONArray("message").getString(0);
                        }
                    }
                    catch(IOException | JSONException e)
                    {
                        Timber.e(e);
                    }
                }
                if(success)
                {
                    listener.tokenValid(token, State.TOKEN_VALID, message);
                }
                else
                {
                    listener.tokenInvalid(token, State.CHECK_REFRESH, message);
                }
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable)
            {
                Timber.e(throwable);
            }
        });
    }

    public static void logoutAccount(Context context)
    {
        new UserLogoutTask(context).execute();
    }


    public interface TokenExistenceListener
    {
        void tokenExists(TokenOrm token);

        void tokenNotExists(int auth);
    }

    public interface TokenValidityListener
    {
        void tokenValid(TokenOrm token, int status, String message);

        void tokenInvalid(TokenOrm token, int status, String massage);
    }

    public static class State
    {
        public static final int NEED_AUTH = 0x01;
        public static final int TOKEN_VALID = 0x02;
        public static final int CHECK_REFRESH = 0x03;
    }

    private static class UserLogoutTask extends AsyncTask<Void, Void, Void>
    {
        private final Context context;

        UserLogoutTask(Context context)
        {
            Timber.d("Constructor");
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            Timber.d("doInBackground");

            SharedPreferences settings = context.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove(this.context.getResources().getString(R.string.shared_preferences_authentication_token));
            editor.remove(this.context.getResources().getString(R.string.shared_preferences_authentication_refresh));
            editor.apply();

            return null;
        }

        @Override
        protected void onPostExecute(Void param)
        {
            @NotNull Intent intent = new Intent(this.context, SplashScreen.class);
            this.context.startActivity(intent);
        }
    }
}
