package app.freelancer.syafiqq.gardureporter.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.dao.SubStationReport;
import app.freelancer.syafiqq.gardureporter.model.request.RawJsonObjectRequest;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import app.freelancer.syafiqq.gardureporter.model.util.Token;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import timber.log.Timber;

public class Dashboard extends AppCompatActivity implements View.OnClickListener, LocationListener
{
    private static final String TAG = Dashboard.class.getSimpleName();

    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;

    private LocationService service;

    // UI
    private TextInputEditText substation;
    private TextInputEditText voltage;
    private TextInputEditText current;
    private Button submit;
    private ProgressBar progress;
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

        this.service = new LocationService();
        this.report = new SubStationReport();
        this.sendTag = "REPORT_SEND";

        if(!checkPermissions())
        {
            requestPermissions();
        }

        this.service.initializeService();
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
        this.progress = (ProgressBar) findViewById(R.id.content_dashboard_progress_bar_submit);

        this.submit.setOnClickListener(this);
        this.submit.setEnabled(this.checkPermissions());
    }

    @Override
    protected void onResume()
    {
        Timber.d("onResume");

        super.onResume();
    }

    @Override
    protected void onPause()
    {
        Timber.d("onPause");

        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Timber.d("onStop");

        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Timber.d("onCreateOptionsMenu");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch(item.getItemId())
        {
            case R.id.activity_dashboard_menu_logout:
                Token.logoutAccount(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onClick(View v)
    {
        Dashboard.this.progress.setVisibility(View.VISIBLE);
        Dashboard.this.submit.setEnabled(false);

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
            this.service.requestLocationUpdates(this);
        }
    }

    @Override public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");
        float suitableMeter = 30f; // adjust your need

        if(location != null)
        {
            Timber.d("Location [%.14g,%.14g] %s", location.getLatitude(), location.getLongitude(), location.getAccuracy());

            if(location.hasAccuracy() && location.getAccuracy() <= suitableMeter)
            {
                // This is your most accurate location.
                this.service.removeLocationUpdates(this);
                Timber.d("Location %s", location.toString());

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
                this.doSubmit(Dashboard.this.report);
            }
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
                        Dashboard.this.progress.setVisibility(View.GONE);
                        Dashboard.this.submit.setEnabled(true);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Dashboard.this.progress.setVisibility(View.GONE);
                        Dashboard.this.submit.setEnabled(true);

                        Timber.e(error);
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

    private static class Bag
    {
        private SubStationReport data;
        private String token;
        private String guard;

        public Bag()
        {
        }
    }

    private final class LocationService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    {
        /**
         * The desired interval for location updates. Inexact. Updates may be more or less frequent.
         */
        private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
        /**
         * The fastest rate for active location updates. Updates will never be more frequent
         * than this value.
         */
        private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
        private GoogleApiClient apiClient;
        private LocationManager locationManager;
        private LocationRequest locationRequest;

        public LocationService()
        {
            Timber.d("Constructor");
        }

        public void initializeService()
        {
            Timber.d("initializeService");

            this.createAPI();
            this.createLocationRequest();
            this.createLocationManager();
        }

        public void requestLocationUpdates(LocationListener listener)
        {
            Timber.d("requestLocationUpdates");

            this.resetGPS();
            try
            {
                LocationServices.FusedLocationApi.requestLocationUpdates(this.apiClient, this.locationRequest, listener);
            }
            catch(SecurityException unlikely)
            {
                Timber.e("Lost location permission. Could not request updates. " + unlikely);
            }
        }

        public void removeLocationUpdates(LocationListener listener)
        {
            Timber.d("removeLocationUpdates");

            try
            {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.apiClient, listener);
            }
            catch(SecurityException unlikely)
            {
                Timber.e("Lost location permission. Could not remove updates. " + unlikely);
            }
        }

        private void resetGPS()
        {
            locationManager.sendExtraCommand(LocationManager.GPS_PROVIDER, "delete_aiding_data", null);
            Bundle bundle = new Bundle();
            locationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
            locationManager.sendExtraCommand("gps", "force_time_injection", bundle);
        }

        private void createLocationManager()
        {
            Timber.d("createLocationManager");

            this.locationManager = (LocationManager) Dashboard.super.getSystemService(LOCATION_SERVICE);
        }

        private void createLocationRequest()
        {
            Timber.d("createLocationRequest");

            this.locationRequest = new LocationRequest();
            this.locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            this.locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }

        private void createAPI()
        {
            Timber.d("createAPI");

            this.apiClient = new GoogleApiClient.Builder(Dashboard.this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            this.apiClient.connect();
        }

        @Override public void onConnected(@Nullable Bundle bundle)
        {
            Timber.d("onConnected");

            try
            {
                LocationServices.FusedLocationApi.getLastLocation(this.apiClient);
            }
            catch(SecurityException unlikely)
            {
                Timber.e("Lost location permission." + unlikely);
            }
        }

        @Override public void onConnectionSuspended(int i)
        {
            Timber.d("onConnectionSuspended");

        }

        @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
        {
            Timber.d("onConnectionFailed");

        }

        public boolean isGPSEnabled(Context ctx)
        {
            @NotNull
            final LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        public boolean isInternetConnected(Context ctx)
        {
            @NotNull
            final ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            @Nullable
            final NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            return activeNetwork != null && (activeNetwork.getState() == NetworkInfo.State.CONNECTED || activeNetwork.getState() == NetworkInfo.State.CONNECTING);
        }
    }


}
