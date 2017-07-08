package app.freelancer.syafiqq.gardureporter.model.util;

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
import android.os.Handler;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.controller.SplashScreen;
import app.freelancer.syafiqq.gardureporter.model.request.RawJsonObjectRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

public class Token
{
    private static final String API_AUTH_CHECK = "check";

    public static void checkTokenExistence(@NotNull final Context context, @NotNull Token.TokenExistenceListener listener)
    {
        Timber.d("checkTokenExistence");

        @NotNull final SharedPreferences settings = context.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, Context.MODE_PRIVATE);
        String token = settings.getString(context.getResources().getString(R.string.shared_preferences_authentication_token), null);
        if(token != null)
        {
            listener.tokenExists(token);
        }
        else
        {
            listener.tokenNotExists(State.NEED_AUTH);
        }
    }

    public static void checkValidity(@NotNull final Context context, final String token, @NotNull final Token.TokenValidityListener listener)
    {
        Timber.d("checkValidity");

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Setting.getOurInstance().getNetworking().getDomain() + "/api/mobile/check?lang=en";

        final JSONObject entry = new JSONObject();
        try
        {
            entry.put("token", token);
            entry.put("guard", Setting.getOurInstance().getNetworking().getGuard());
        }
        catch(JSONException e)
        {
            Timber.e(e);
        }

        // Request a string response from the provided URL.
        final RawJsonObjectRequest request = new RawJsonObjectRequest(
                Request.Method.POST,
                url,
                entry.toString(),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        String message = null;
                        boolean success = false;
                        try
                        {
                            JSONObject data = response.getJSONObject("data");
                            int status = data.getInt("status");
                            JSONArray messages = data.getJSONArray("message");
                            message = messages.getString(0);
                            if(status == 1)
                            {
                                success = true;
                            }
                        }
                        catch(JSONException e)
                        {
                            Timber.e(e);
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
                },
                new Response.ErrorListener()
                {
                    public static final int SECONDS_DELAYED = 2;

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        final NetworkResponse response = error.networkResponse;
                        if(error instanceof ServerError && response != null)
                        {
                            try
                            {
                                final String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Timber.e(res);
                            }
                            catch(UnsupportedEncodingException e1)
                            {
                                Timber.e(e1);
                            }
                            new Handler().postDelayed(new Runnable()
                            {
                                public void run()
                                {
                                    Token.checkValidity(context, token, listener);
                                }
                            }, SECONDS_DELAYED * 1000);
                        }
                    }
                }

        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Requested-With", "XMLHttpRequest");
                headers.put("X-Access-Permission", Setting.getOurInstance().getNetworking().getCertificate());
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Add the request to the RequestQueue.

        request.setTag(API_AUTH_CHECK);
        queue.add(request);
    }

    public static void logoutAccount(Context context)
    {
        new UserLogoutTask(context).execute();
    }


    public interface TokenExistenceListener
    {
        void tokenExists(String token);

        void tokenNotExists(int needAuth);
    }

    public interface TokenValidityListener
    {
        void tokenValid(String token, int status, String message);

        void tokenInvalid(String token, int status, String massage);
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
