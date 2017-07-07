package app.freelancer.syafiqq.gardureporter.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.request.RawJsonObjectRequest;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
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
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class AuthLogin extends AppCompatActivity
{
    private static final String API_AUTH_LOGIN = "api_auth_login";
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private int jumper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_auth_login);

        Intent intent = super.getIntent();
        this.jumper = intent.getIntExtra(Setting.Jumper.NAME, Setting.Jumper.CLASS_DASHBOARD);

        // Set up the login form.
        this.mEmailView = (EditText) findViewById(R.id.activity_auth_login_edit_text_form_email);

        this.mPasswordView = (EditText) findViewById(R.id.activity_auth_login_edit_text_form_password);
        this.mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if(id == R.id.activity_auth_login_button_form_submit || id == EditorInfo.IME_NULL)
                {
                    AuthLogin.this.attemptLogin();
                    return true;
                }
                return false;
            }
        });

        @NotNull final Button mEmailSignInButton = (Button) findViewById(R.id.activity_auth_login_button_form_submit);
        mEmailSignInButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptLogin();
            }
        });

        this.mLoginFormView = findViewById(R.id.activity_auth_login_scroll_view_login_form);
        this.mProgressView = findViewById(R.id.activity_auth_login_progress_bar_login);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {
        if(this.mAuthTask != null)
        {
            return;
        }

        // Reset errors.
        this.mEmailView.setError(null);
        this.mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        @NotNull final String email = mEmailView.getText().toString();
        @NotNull final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        @Nullable View focusView = null;

        if(TextUtils.isEmpty(password))
        {
            this.mPasswordView.setError(getString(R.string.error_field_required));
            focusView = this.mPasswordView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if(!this.isPasswordValid(password))
        {
            this.mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = this.mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if(TextUtils.isEmpty(email))
        {
            this.mEmailView.setError(getString(R.string.error_field_required));
            focusView = this.mEmailView;
            cancel = true;
        }
        else if(!isEmailValid(email))
        {
            this.mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = this.mEmailView;
            cancel = true;
        }

        if(cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            this.showProgress(true);
            this.mAuthTask = new UserLoginTask(email, password);
            this.mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email)
    {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        this.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        this.mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        this.mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        this.mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Void>
    {
        private final String mEmail;
        private final String mPassword;
        private RequestQueue queue;

        UserLoginTask(String email, String password)
        {
            this.mEmail = email;
            this.mPassword = password;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            Timber.d("doInBackground");

            if(this.queue == null)
            {
                this.queue = Volley.newRequestQueue(AuthLogin.this);
            }
            String url = Setting.getOurInstance().getNetworking().getDomain() + "/api/mobile/login?lang=en";

            final JSONObject entry = new JSONObject();
            try
            {
                entry.put("identity", this.mEmail);
                entry.put("password", this.mPassword);
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
                                    JSONObject tokens = data.getJSONObject("token");
                                    String token = tokens.getString("token");
                                    String refresh = tokens.getString("refresh");
                                    SharedPreferences settings = getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor.putString(AuthLogin.super.getResources().getString(R.string.shared_preferences_authentication_token), token);
                                    editor.putString(AuthLogin.super.getResources().getString(R.string.shared_preferences_authentication_refresh), refresh);
                                    editor.apply();
                                    success = true;
                                }
                            }
                            catch(JSONException e)
                            {
                                Timber.e(e);
                            }
                            UserLoginTask.this.onPostExecute(success, message);
                        }
                    },
                    new Response.ErrorListener()
                    {
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
                                    Toast.makeText(AuthLogin.this, AuthLogin.super.getResources().getString(R.string.global_toast_error_sending_to_server), Toast.LENGTH_SHORT).show();
                                }
                                catch(UnsupportedEncodingException e1)
                                {
                                    Timber.e(e1);
                                }
                            }
                            UserLoginTask.this.onPostExecute(false, null);
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

            request.setTag(API_AUTH_LOGIN);
            this.queue.add(request);

            return null;
        }

        protected void onPostExecute(final Boolean success, final String message)
        {
            AuthLogin.this.mAuthTask = null;
            AuthLogin.this.showProgress(false);

            if(message != null)
            {
                Toast.makeText(AuthLogin.this, message, Toast.LENGTH_SHORT).show();
            }

            if(success)
            {
                @Nullable Intent intent = null;
                switch(AuthLogin.this.jumper)
                {
                    case Setting.Jumper.CLASS_DASHBOARD:
                    {
                        intent = new Intent(AuthLogin.this, Dashboard.class);
                    }
                    break;
                    default:
                    {
                        intent = new Intent(AuthLogin.this, AuthLogin.class);
                    }
                    break;
                }
                AuthLogin.super.startActivity(intent);
                AuthLogin.super.finish();
            }
            else
            {
                AuthLogin.this.mEmailView.setError(getString(R.string.error_incorrect_login));
                AuthLogin.this.mPasswordView.setError(getString(R.string.error_incorrect_login));
                AuthLogin.this.mEmailView.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            AuthLogin.this.mAuthTask = null;
            AuthLogin.this.showProgress(false);
        }
    }
}

