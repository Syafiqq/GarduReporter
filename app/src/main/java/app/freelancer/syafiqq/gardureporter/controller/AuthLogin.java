package app.freelancer.syafiqq.gardureporter.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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
import app.freelancer.syafiqq.gardureporter.R;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class AuthLogin extends AppCompatActivity
{
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_auth_login);
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
                    Timber.d("Login");
                    //AuthLogin.this.attemptLogin();
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
        if(this.isPasswordValid(password))
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
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password)
        {
            this.mEmail = email;
            this.mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                // Simulate network access.
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            AuthLogin.this.mAuthTask = null;
            AuthLogin.this.showProgress(false);

            if(success)
            {
                //AuthLogin.this.finish();
            }
            else
            {
                AuthLogin.this.mPasswordView.setError(getString(R.string.error_incorrect_password));
                AuthLogin.this.mPasswordView.requestFocus();
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

