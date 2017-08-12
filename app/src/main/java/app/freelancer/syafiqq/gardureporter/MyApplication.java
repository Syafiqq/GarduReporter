package app.freelancer.syafiqq.gardureporter;

import android.app.Application;
import android.content.Context;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import timber.log.Timber;


/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 11 August 2017, 9:57 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
@ReportsCrashes(mailTo = "syafiq.rezpector@gmail.com",
                customReportContent = {ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT},
                mode = ReportingInteractionMode.TOAST,
                resToastText = R.string.crash_toast_text)
public class MyApplication extends Application
{

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        this.initializeTimber();
        ACRA.init(this);
    }

    private void initializeTimber()
    {
        if(BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("initializeTimber");
    }
}
