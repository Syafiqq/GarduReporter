package app.freelancer.syafiqq.gardureporter.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import timber.log.Timber;

public class SplashScreen extends AppCompatActivity
{

    private static final int SECONDS_DELAYED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash_screen);

        this.initializeTimber();
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... voids)
            {

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                new Handler().postDelayed(new Runnable()
                {
                    public void run()
                    {
                        //SplashScreen.super.startActivity(new Intent(SplashScreen.this, Dashboard.class));
                        //SplashScreen.super.finish();
                    }
                }, SECONDS_DELAYED * 1000);
            }
        }.execute();
    }

    private void initializeTimber()
    {
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
