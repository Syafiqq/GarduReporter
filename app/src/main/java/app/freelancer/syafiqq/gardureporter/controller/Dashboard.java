package app.freelancer.syafiqq.gardureporter.controller;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.dao.SubStationReport;
import app.freelancer.syafiqq.gardureporter.model.request.RawJsonObjectRequest;
import app.freelancer.syafiqq.gardureporter.model.service.LocationService;
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
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import timber.log.Timber;

public class Dashboard extends AppCompatActivity implements View.OnClickListener
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
    private RequestQueue queue;
    private String sendTag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timber.d("onCreate");

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_dashboard);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_dashboard_toolbar_toolbar);
        super.setSupportActionBar(toolbar);

        this.gpsReceiver = new GPSReceiver();
        this.report = new SubStationReport();
        this.sendTag = "REPORT_SEND";

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

        this.submit.setOnClickListener(this);
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
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults)
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

    private void doSubmit(final SubStationReport report)
    {
        Timber.d("doSumbit");
        @NotNull final SharedPreferences settings = super.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, Context.MODE_PRIVATE);
        final String token = settings.getString(super.getResources().getString(R.string.shared_preferences_authentication_token), null);

        Bag bag = new Bag();
        bag.data = report;
        bag.token = token;
        bag.guard = Setting.getOurInstance().getNetworking().getGuard();

        final Gson gson = new Gson();
        String data = gson.toJson(bag);

        if(this.queue == null)
        {
            this.queue = Volley.newRequestQueue(this);
        }
        String url = Setting.getOurInstance().getNetworking().getDomain() + "/api/mobile/insert?lang=en";

        // Request a string response from the provided URL.
        final RawJsonObjectRequest request = new RawJsonObjectRequest(
                Request.Method.POST,
                url,
                data,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Timber.d(response.toString());
                        Toast.makeText(Dashboard.this, Dashboard.super.getResources().getString(R.string.global_toast_success_sending_to_server), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(Dashboard.this, Dashboard.super.getResources().getString(R.string.global_toast_error_sending_to_server), Toast.LENGTH_SHORT).show();
                            }
                            catch(UnsupportedEncodingException e1)
                            {
                                Timber.e(e1);
                            }
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

        request.setTag(this.sendTag);
        this.queue.add(request);
    }

    @Override
    public void onClick(View v)
    {
        final SubStationReport report1 = this.report;
        report1.setSubstation(this.substation.getText().toString());
        report1.setVoltage(Double.parseDouble(this.voltage.getText().toString()));
        report1.setCurrent(Double.parseDouble(this.current.getText().toString()));
        if(!checkPermissions())
        {
            requestPermissions();
        }
        else
        {
            this.locationService.requestLocationUpdates();
        }
    }

    private static class Bag
    {
        private SubStationReport data;
        private String token;
        private String guard;

        public Bag()
        {
        }
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
                Timber.d("Location=[%g %g]", location.getLatitude(), location.getLongitude());

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
