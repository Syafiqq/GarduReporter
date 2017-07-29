package app.freelancer.syafiqq.gardureporter.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import java.util.concurrent.CountDownLatch;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class SplashScreen extends AppCompatActivity
{
    private static final int SECONDS_DELAYED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash_screen);
        Timber.d("Constructor");

        new TokenValidationTask().execute();
    }

    @Override protected void onStart()
    {
        super.onStart();
        this.initializeTimber();
    }

    private void initializeTimber()
    {
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("initializeTimber");
    }

    private class TokenValidationTask extends AsyncTask<Void, Void, Void> implements TokenDao.TokenExistenceListener, TokenDao.TokenValidityListener
    {
        private CountDownLatch checkLatch;
        private int state = 0x0;

        @Override protected void onPreExecute()
        {
            Timber.d("onPreExecute");

            super.onPreExecute();
            this.checkLatch = new CountDownLatch(1);
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            Timber.d("doInBackground");

            TokenDao.checkTokenExistence(SplashScreen.this, this);
            try
            {
                this.checkLatch.await();
            }
            catch(InterruptedException e)
            {
                Timber.e(e);
            }
            return null;
        }

        @Override protected void onPostExecute(Void aVoid)
        {
            Timber.d("onPostExecute");
            super.onPostExecute(aVoid);
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    @NotNull Intent intent;
                    switch(TokenValidationTask.this.state)
                    {
                        case TokenDao.State.NEED_AUTH:
                        {
                            intent = new Intent(SplashScreen.this, AuthLogin.class);
                            intent.putExtra(Setting.Jumper.NAME, Setting.Jumper.CLASS_DASHBOARD);
                        }
                        break;
                        default:
                        {
                            intent = new Intent(SplashScreen.this, Dashboard.class);
                        }
                        break;
                    }
                    SplashScreen.super.startActivity(intent);
                    SplashScreen.super.finish();
                }
            }, SECONDS_DELAYED * 1000);
        }

        @Override public void tokenExists(TokenOrm token)
        {
            Timber.d("tokenExists");

            TokenDao.checkValidity(SplashScreen.this, token, this);
        }

        @Override public void tokenNotExists(int state)
        {
            Timber.d("tokenNotExists");

            this.state = state;
            this.checkLatch.countDown();
        }

        @Override public void tokenValid(TokenOrm token, int status, String message)
        {
            Timber.d("tokenValid");

            this.checkLatch.countDown();
        }

        @Override public void tokenInvalid(TokenOrm token, int status, String massage)
        {
            Timber.d("tokenInvalid");

            this.state = TokenDao.State.NEED_AUTH;
            this.checkLatch.countDown();
        }
    }
}
