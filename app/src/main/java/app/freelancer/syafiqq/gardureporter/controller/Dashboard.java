package app.freelancer.syafiqq.gardureporter.controller;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.dao.SubStationReport;
import app.freelancer.syafiqq.gardureporter.model.service.LocationService;
import timber.log.Timber;

public class Dashboard extends AppCompatActivity
{
    private static final String TAG = Dashboard.class.getSimpleName();

    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;

    // The BroadcastReceiver used to listen from broadcasts from the service.
    private GPSReceiver gpsReceiver;

    // A reference to the service used to get location updates.
    private LocationService locationService = null;

    // Tracks the bound state of the service.
    // Monitors the state of the connection to the service.
    private final ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            final LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            Dashboard.this.locationService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Dashboard.this.locationService = null;
        }
    };
    // UI
    private TextInputEditText substation;
    private TextInputEditText voltage;
    private TextInputEditText current;
    private Button submit;
    //DAO
    private SubStationReport report;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timber.d("onCreate");

        super.onCreate(savedInstanceState);
        Timber.plant(new Timber.DebugTree());

        super.setContentView(R.layout.activity_dashboard);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_dashboard_toolbar_toolbar);
        super.setSupportActionBar(toolbar);

        this.gpsReceiver = new GPSReceiver();
        this.report = new SubStationReport();

        if(!checkPermissions())
        {
            requestPermissions();
        }
    }

    @Override
    protected void onStart()
    {
        Timber.d("onStart");

        super.onStart();
        this.substation = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_substation);
        this.voltage = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_voltage);
        this.current = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_current);
        this.submit = (Button) findViewById(R.id.content_dashboard_button_submit);

        this.submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final SubStationReport report = Dashboard.this.report;
                report.setSubstation(Dashboard.this.substation.getText().toString());
                report.setVoltage(Double.parseDouble(Dashboard.this.voltage.getText().toString()));
                report.setCurrent(Double.parseDouble(Dashboard.this.current.getText().toString()));
                if(!checkPermissions())
                {
                    requestPermissions();
                }
                else
                {
                    Dashboard.this.locationService.requestLocationUpdates();
                }
            }
        });
        this.submit.setEnabled(this.checkPermissions());

        super.bindService(new Intent(this, LocationService.class), this.serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume()
    {
        Timber.d("onResume");

        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.gpsReceiver, new IntentFilter(LocationService.ACTION_BROADCAST));
    }

    @Override
    protected void onPause()
    {
        Timber.d("onPause");

        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.gpsReceiver);
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Timber.d("onStop");

        super.unbindService(this.serviceConnection);
        super.onStop();
    }

    /*
     * Returns the current state of the permissions needed.
     */
    private boolean checkPermissions()
    {
        Timber.d("checkPermissions");

        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void requestPermissions()
    {
        Timber.d("requestPermissions");

        final boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION);

        /* Provide an additional rationale to the user. This would happen if the user denied the
         request previously, but didn't check the "Don't ask again" checkbox.*/
        if(shouldProvideRationale)
        {
            Timber.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    findViewById(R.id.activity_dashboard_root),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.command_ok, new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            // Request permission
                            ActivityCompat.requestPermissions(Dashboard.this,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        }
        else
        {
            Timber.i(TAG, "Requesting permission");
            /* Request permission. It's possible this can be auto answered if device policy
             sets the permission in a given state or the user denied the permission
             previously and checked "Never ask again".*/
            ActivityCompat.requestPermissions(Dashboard.this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /*
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        Timber.d("onRequestPermissionsResult");

        if(requestCode == REQUEST_PERMISSIONS_REQUEST_CODE)
        {
            if(grantResults.length <= 0)
            {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Timber.i(TAG, "User interaction was cancelled.");
            }
            else if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // Permission was granted.
                this.locationService.requestLocationUpdates();
            }
            else
            {
                // Permission denied.
                Snackbar.make(
                        findViewById(R.id.activity_dashboard_root),
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.command_setting, new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
            this.submit.setEnabled(this.checkPermissions());
        }
    }

    private void doSubmit(SubStationReport report)
    {
        Timber.d("doSumbit");

        final Gson gson = new Gson();
        Timber.d("%s", gson.toJson(report));
    }

    private final class GPSReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Timber.d("onReceive");

            final Location location = intent.getParcelableExtra(LocationService.EXTRA_LOCATION);
            if(location != null)
            {
                Timber.d("Lat=[%g], Lgt=[%g]", location.getLatitude(), location.getLongitude());
                Timber.d("Location=[%.16g %.16g]", location.getLatitude(), location.getLongitude());

                final SubStationReport report = Dashboard.this.report;
                if(report.getLocation() == null)
                {
                    report.setLocation(new app.freelancer.syafiqq.gardureporter.model.dao.Location(0., 0.));
                }

                final app.freelancer.syafiqq.gardureporter.model.dao.Location reportLocation = report.getLocation();
                if(reportLocation != null)
                {
                    reportLocation.setLatitude(location.getLatitude());
                    reportLocation.setLongitude(location.getLongitude());
                }
                Dashboard.this.doSubmit(Dashboard.this.report);

                Dashboard.this.locationService.removeLocationUpdates();
            }
        }
    }
}
