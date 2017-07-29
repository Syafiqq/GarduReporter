package app.freelancer.syafiqq.gardureporter.model.dao;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 29 July 2017, 4:48 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.content.Context;
import android.support.annotation.NonNull;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.UserOrm;
import app.freelancer.syafiqq.gardureporter.model.service.AuthService;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class AuthDao
{
    public static void login(final Context context, UserOrm user, final LoginRequestListener listener)
    {
        Timber.d("login");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.getOurInstance().getNetworking().getDomain())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Setting.Networking.getReservedClient(context))
                .build();
        @NotNull final AuthService authService = retrofit.create(AuthService.class);
        authService.login("admin", user).enqueue(new Callback<ResponseBody>()
        {
            @Override public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                String message = null;
                int success = 0;
                TokenOrm token = null;
                @Nullable ResponseBody body = response.body();
                if(body != null)
                {
                    try
                    {
                        JSONObject res = new JSONObject(body.string());
                        JSONObject data = res.optJSONObject("data");
                        if(data != null)
                        {
                            success = data.optInt("status");
                            JSONArray messages = data.optJSONArray("message");
                            if(messages != null)
                            {
                                message = messages.optString(0);
                            }
                            JSONObject tokens = data.optJSONObject("token");
                            if(tokens != null)
                            {
                                token = new TokenOrm(tokens.optString("token"), tokens.optString("refresh"));
                            }
                        }
                    }
                    catch(IOException | JSONException e)
                    {
                        Timber.e(e);
                    }
                }
                if((success > 0) && (token != null))
                {
                    listener.onLoginSuccessful(token, AuthDao.LoginState.SUCCESS, message);
                }
                else
                {
                    listener.onLoginFailed(AuthDao.LoginState.FAILED, message);
                }
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable)
            {
                listener.onLoginFailed(AuthDao.LoginState.FAILED, context.getResources().getString(R.string.global_toast_error_sending_to_server));
                Timber.e(throwable);
            }
        });
    }

    public interface LoginRequestListener
    {
        void onLoginFailed(int status, String message);

        void onLoginSuccessful(TokenOrm orm, int status, String message);
    }

    public static class LoginState
    {
        public static final int SUCCESS = 0x1;
        public static final int FAILED = 0x0;
    }
}
