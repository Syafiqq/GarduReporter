package app.freelancer.syafiqq.gardureporter.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
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

        this.initializeTimber();
        new AsyncTask<Void, Void, Void>()
        {
            private final int BYPASS_AUTH = 0x01;
            private final int NEED_AUTH = 0x02;

            private int state = BYPASS_AUTH;

            @Override
            protected Void doInBackground(Void... voids)
            {
                SharedPreferences settings = SplashScreen.super.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, MODE_PRIVATE);
                if(settings.contains(SplashScreen.super.getResources().getString(R.string.shared_preferences_authentication_token)))
                {
                    Timber.d("Yes Token");
                }
                else
                {
                    this.state = NEED_AUTH;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                new Handler().postDelayed(new Runnable()
                {
                    public void run()
                    {
                        @NotNull Intent intent;
                        switch(state)
                        {
                            case NEED_AUTH:
                            {
                                intent = new Intent(SplashScreen.this, AuthLogin.class);
                                intent.putExtra(Setting.Jumper.NAME, Setting.Jumper.CLASS_DASHBOARD);
                            }
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
