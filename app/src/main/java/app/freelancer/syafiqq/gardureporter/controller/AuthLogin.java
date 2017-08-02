package app.freelancer.syafiqq.gardureporter.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
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
import app.freelancer.syafiqq.gardureporter.model.dao.AuthDao;
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.UserOrm;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class AuthLogin extends AppCompatActivity implements AuthDao.LoginRequestListener
{
    private static final String API_AUTH_LOGIN = "api_auth_login";
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserOrm user;
    private int jumper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timber.d("onCreate");

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_auth_login);

        Intent intent = super.getIntent();
        this.jumper = intent.getIntExtra(Setting.Jumper.NAME, Setting.Jumper.CLASS_DASHBOARD);
        this.user = new UserOrm();

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
        Timber.d("attemptLogin");

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
            this.user.setEmail(email);
            this.user.setPassword(password);
            AuthDao.login(this.getApplicationContext(), this.user, this);
        }
    }

    private boolean isEmailValid(String email)
    {
        Timber.d("isEmailValid");

        return email.contains("@");
    }

    private boolean isPasswordValid(String password)
    {
        Timber.d("isPasswordValid");

        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        Timber.d("showProgress");

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

    @Override public void onLoginFailed(int status, String message)
    {
        Timber.d("onLoginFailed");

        this.postLogin(false, message);
    }

    @Override public void onLoginSuccessful(TokenOrm token, int status, String message)
    {
        Timber.d("onLoginSuccessful");

        TokenDao.storeToken(this.getApplicationContext(), token);
        this.postLogin(status > 0, message);
    }

    private void postLogin(boolean success, String message)
    {
        Timber.d("postLogin");

        AuthLogin.this.showProgress(false);

        if(message != null)
        {
            Toast.makeText(AuthLogin.this, message, Toast.LENGTH_SHORT).show();
        }

        if(success)
        {
            @Nullable Intent intent;
            switch(AuthLogin.this.jumper)
            {
                case Setting.Jumper.CLASS_DASHBOARD:
                {
                    intent = new Intent(AuthLogin.this, Dashboard.class);
                }
                break;
                case Setting.Jumper.CLASS_GARDU_INDEX_MEASUREMENT:
                {
                    intent = new Intent(AuthLogin.this, GarduIndexMeasurement.class);
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
            if(message != null)
            {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

