package app.freelancer.syafiqq.gardureporter.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.BuildConfig;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.controller.adapter.GarduIndukAdapter;
import app.freelancer.syafiqq.gardureporter.controller.adapter.GarduPenyulangAdapter;
import app.freelancer.syafiqq.gardureporter.controller.adapter.JenisGarduAdapter;
import app.freelancer.syafiqq.gardureporter.model.custom.android.location.BooleanObserver;
import app.freelancer.syafiqq.gardureporter.model.custom.android.location.ObservableLocation;
import app.freelancer.syafiqq.gardureporter.model.dao.GarduDao;
import app.freelancer.syafiqq.gardureporter.model.dao.SubStationReport;
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.gson.serializer.custom.Location14DigitSerializer;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndukOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduPenyulangOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.JenisGarduOrm;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import timber.log.Timber;

public class Dashboard extends AppCompatActivity implements View.OnClickListener, LocationListener
{
    private static final String TAG = Dashboard.class.getSimpleName();

    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;
    private static final int SECONDS_DELAYED = 30;
    private static final int REQUEST_CHECK_SETTINGS = 0x01;

    private LocationService service;
    private ObservableLocation location;

    // UI
    private TextInputEditText substation;
    private TextInputEditText voltage;
    private TextInputEditText current;
    private Button submit;
    private Switch locationRequestToggle;
    private ProgressBar progress;
    //DAO
    private SubStationReport report;
    private RequestQueue queue;
    private String sendTag;
    //Observer
    private Observer accuracyObserver;
    private Observer serviceObserver;
    private Handler handler;
    private Runnable asyncOverrideLocationRequest;
    private boolean isSubmitRequested;
    private SearchableSpinner garduInduk;
    private SearchableSpinner garduPenyulang;
    private Spinner jenisGardu;

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
        this.location = new ObservableLocation(null);
        this.handler = new Handler();
        this.sendTag = "REPORT_SEND";

        if(!this.checkPermissions())
        {
            this.requestPermissions();
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
        this.locationRequestToggle = (Switch) findViewById(R.id.content_dashboard_switch_location_accuracy);
        this.progress = (ProgressBar) findViewById(R.id.content_dashboard_progress_bar_submit);
        this.garduInduk = (SearchableSpinner) findViewById(R.id.content_dashboard_searchablespinner_induk);
        this.garduPenyulang = (SearchableSpinner) findViewById(R.id.content_dashboard_searchablespinner_penyulang);
        this.jenisGardu = (Spinner) findViewById(R.id.content_dashboard_spinner_jenisgardu);
        final ImageButton garduIndukSync = (ImageButton) findViewById(R.id.content_dashboard_imagebutton_induk_refresh);
        final ImageButton garduPenyulangSync = (ImageButton) findViewById(R.id.content_dashboard_imagebutton_penyulang_refresh);
        this.isSubmitRequested = false;

        this.updateAccuracy(this.location.getLocation());
        this.accuracyObserver = new Observer()
        {
            @Override public void update(Observable observable, Object o)
            {
                Dashboard.this.updateAccuracy((Location) o);
            }
        };
        this.serviceObserver = new Observer()
        {
            @Override public void update(Observable observable, Object o)
            {
                BooleanObserver bool = (BooleanObserver) observable;
                final boolean resultedPermission = Dashboard.this.checkPermissions() && bool.isBool();
                Dashboard.this.shiftUISubmit(resultedPermission);
            }
        };
        this.asyncOverrideLocationRequest = new Runnable()
        {
            @Override public void run()
            {
                @Nullable final Location location = Dashboard.this.location.getLocation();
                if(location == null)
                {
                    Toast.makeText(Dashboard.this, Dashboard.super.getResources().getString(R.string.error_no_location_retrieved), Toast.LENGTH_SHORT).show();
                    Dashboard.this.isSubmitRequested = false;
                    Dashboard.this.service.removeLocationUpdates(Dashboard.this);
                    Dashboard.this.shiftUISubmit(true);
                }
                else
                {
                    Dashboard.this.prepareSubmit(location);
                }
            }
        };

        this.submit.setOnClickListener(this);
        this.locationRequestToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                {
                    Dashboard.this.onLocationRequestSwitchedOn();
                }
                else
                {
                    Dashboard.this.onLocationRequestSwitchedOff();
                }
            }
        });
        this.location.addObserver(this.accuracyObserver);
        this.service.availability.addObserver(this.serviceObserver);

        final ArrayAdapter<GarduIndukOrm> garduIndukAdapter = new GarduIndukAdapter(super.getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<GarduIndukOrm>());
        final ArrayAdapter<GarduPenyulangOrm> garduPenyulangAdapter = new GarduPenyulangAdapter(super.getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<GarduPenyulangOrm>());
        final ArrayAdapter<JenisGarduOrm> jenisGarduAdapter = new JenisGarduAdapter(super.getApplicationContext(), android.R.layout.simple_spinner_item, new JenisGarduOrm[] {
                new JenisGarduOrm("Portal", "Portal")
                , new JenisGarduOrm("Cantol", "Cantol")
        });
        garduIndukAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        garduPenyulangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisGarduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        this.garduInduk.setAdapter(garduIndukAdapter);
        this.garduInduk.setTitle("Select Gardu Induk");
        this.garduInduk.setPositiveButton("OK");
        this.garduPenyulang.setAdapter(garduPenyulangAdapter);
        this.garduPenyulang.setTitle("Select Gardu Penyulang");
        this.garduPenyulang.setPositiveButton("OK");
        this.jenisGardu.setAdapter(jenisGarduAdapter);

        garduIndukSync.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                GarduDao.findAllInduk(Dashboard.super.getApplicationContext(), new GarduDao.GarduRequestListener<List<GarduIndukOrm>>()
                {
                    @Override public void onRequestFailed(int status, String message)
                    {
                        Timber.d("onRequestFailed");

                        if(message != null)
                        {
                            Toast.makeText(Dashboard.this, "message", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onRequestSuccessful(List<GarduIndukOrm> gardu, int status, String message)
                    {
                        Timber.d("onRequestSuccessful");

                        ((GarduIndukAdapter) garduIndukAdapter).update(gardu);
                        garduIndukAdapter.notifyDataSetChanged();
                        this.onRequestFailed(status, message);
                    }
                });
            }
        });
        garduPenyulangSync.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                GarduDao.findAllPenyulang(Dashboard.super.getApplicationContext(), new GarduDao.GarduRequestListener<List<GarduPenyulangOrm>>()
                {
                    @Override public void onRequestFailed(int status, String message)
                    {
                        Timber.d("onRequestFailed");

                        if(message != null)
                        {
                            Toast.makeText(Dashboard.this, "message", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onRequestSuccessful(List<GarduPenyulangOrm> gardu, int status, String message)
                    {
                        Timber.d("onRequestSuccessful");

                        ((GarduPenyulangAdapter) garduPenyulangAdapter).update(gardu);
                        garduPenyulangAdapter.notifyDataSetChanged();
                        this.onRequestFailed(status, message);
                    }
                });
            }
        });
        garduIndukSync.callOnClick();
        garduPenyulangSync.callOnClick();
    }

    @Override protected void onResume()
    {
        Timber.d("onResume");

        this.serviceObserver.update(this.service.availability, false);

        super.onResume();
    }

    @Override protected void onStop()
    {
        Timber.d("onStop");

        this.location.deleteObserver(this.accuracyObserver);
        this.service.availability.deleteObserver(this.accuracyObserver);

        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        Timber.d("onDestroy");

        this.service.destroyService();
        super.onDestroy();
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
        Timber.d("onOptionsItemSelected");

        // Handle item selection
        switch(item.getItemId())
        {
            case R.id.activity_dashboard_menu_logout:
                TokenDao.logoutAccount(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case Dashboard.REQUEST_CHECK_SETTINGS:
            {
                switch(resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        Timber.d("User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                    }
                    break;
                    case Activity.RESULT_CANCELED:
                    {
                        Timber.d("User chose not to make required location settings changes.");
                    }
                    break;
                }
                this.onLocationRequestSwitchedOff();
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*============================================================================================*/

    private void updateAccuracy(@Nullable Location location)
    {
        Timber.d("updateAccuracy");

        this.locationRequestToggle.setText(String.format(Locale.getDefault(), super.getResources().getString(R.string.content_dashboard_accuracy_label), location == null ? Float.POSITIVE_INFINITY : location.getAccuracy()));
    }

    private void onLocationRequestSwitchedOn()
    {
        Timber.d("onLocationRequestSwitchedOn");

        if(!this.checkPermissions())
        {
            this.requestPermissions();
        }
        else
        {
            this.service.requestLocationUpdates(this);
        }
    }

    private void onLocationRequestSwitchedOff()
    {
        Timber.d("onLocationRequestSwitchedOn");

        this.shiftUISubmit(true);
        this.locationRequestToggle.setChecked(false);
        this.service.removeLocationUpdates(Dashboard.this);
        this.location.setLocation(null);
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
            else
            {
                //noinspection StatementWithEmptyBody
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    this.serviceObserver.update(this.service.availability, false);
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
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        this.shiftUISubmit(false);

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
            this.locationRequestToggle.setChecked(true);
            this.isSubmitRequested = true;
            this.handler.postDelayed(this.asyncOverrideLocationRequest, SECONDS_DELAYED * 1000);
        }
    }

    @Override public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");

        if(location != null)
        {
            Timber.d("Location [%.14g,%.14g] %s", location.getLatitude(), location.getLongitude(), location.getAccuracy());
            if(location.hasAccuracy())
            {
                if((this.location.getLocation() == null ? Float.MAX_VALUE : this.location.getLocation().getAccuracy()) > location.getAccuracy())
                {
                    this.location.setLocation(location);
                }

                if(this.isSubmitRequested)
                {
                    if(this.location.getLocation().getAccuracy() <= LocationService.DISTANCE_ERROR_THRESHOLD_IN_METERS)
                    {
                        this.prepareSubmit(this.location.getLocation());
                    }
                }
            }
        }
    }

    private void prepareSubmit(@NotNull Location location)
    {
        this.handler.removeCallbacks(this.asyncOverrideLocationRequest);
        this.isSubmitRequested = false;
        this.service.removeLocationUpdates(this);

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

    private void doSubmit(final SubStationReport report)
    {
        Timber.d("doSumbit");
        @NotNull final SharedPreferences settings = super.getSharedPreferences(Setting.SharedPreferences.SHARED_PREFERENCES_AUTHENTICATION, Context.MODE_PRIVATE);
        final String token = settings.getString(super.getResources().getString(R.string.shared_preferences_authentication_token), null);

        Bag bag = new Bag();
        bag.data = report;
        bag.token = token;
        bag.guard = Setting.getOurInstance().getNetworking().getGuard();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(app.freelancer.syafiqq.gardureporter.model.dao.Location.class, new Location14DigitSerializer());
        Gson gson = gsonBuilder.create();
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
                        Dashboard.this.shiftUISubmit(true);
                        Dashboard.this.onLocationRequestSwitchedOff();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
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
                        Dashboard.this.shiftUISubmit(true);
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

    private void shiftUISubmit(boolean finished)
    {
        if(finished)
        {
            this.progress.setVisibility(View.GONE);
            this.submit.setEnabled(true);
            this.locationRequestToggle.setEnabled(true);
        }
        else
        {
            this.progress.setVisibility(View.VISIBLE);
            this.submit.setEnabled(false);
            this.locationRequestToggle.setEnabled(false);
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

    private final class LocationService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    {
        /**
         * The desired interval for location updates. Inexact. Updates may be more or less frequent.
         */
        private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 7500;
        /**
         * The fastest rate for active location updates. Updates will never be more frequent
         * than this value.
         */
        private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

        private static final float DISTANCE_ERROR_THRESHOLD_IN_METERS = 20;
        public BooleanObserver availability;
        public boolean isAlreadyRequested;
        /**
         * Provides the entry point to Google Play services.
         */
        protected GoogleApiClient apiClient;
        /**
         * Stores parameters for requests to the FusedLocationProviderApi.
         */
        protected LocationRequest locationRequest;
        /**
         * Stores the types of location services the client is interested in using. Used for checking
         * settings to determine if the device has optimal location settings.
         */
        protected LocationSettingsRequest locationSettingsRequest;
        /**
         * Represents a geographical location.
         */
        protected Location mCurrentLocation;
        private LocationManager locationManager;

        public LocationService()
        {
            Timber.d("Constructor");
            this.availability = new BooleanObserver(false);
            this.isAlreadyRequested = false;
        }

        public void initializeService()
        {
            Timber.d("initializeService");

            this.createAPI();
            this.createLocationRequest();
            this.createLocationManager();
            this.buildLocationSettingsRequest();
        }

        /**
         * Uses a {@link com.google.android.gms.location.LocationSettingsRequest.Builder} to build
         * a {@link com.google.android.gms.location.LocationSettingsRequest} that is used for checking
         * if a device has the needed location settings.
         */
        protected void buildLocationSettingsRequest()
        {
            final LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(this.locationRequest);
            locationSettingsRequest = builder.build();
        }

        public void requestLocationUpdates(final LocationListener listener)
        {
            Timber.d("requestLocationUpdates");

            this.resetGPS();
            try
            {
                if(!this.isAlreadyRequested)
                {
                    //LocationServices.FusedLocationApi.requestLocationUpdates(this.apiClient, this.locationRequest, listener);
                    LocationServices.SettingsApi.checkLocationSettings(
                            this.apiClient,
                            this.locationSettingsRequest
                    ).setResultCallback(new ResultCallback<LocationSettingsResult>()
                    {
                        @Override
                        public void onResult(@NonNull LocationSettingsResult locationSettingsResult)
                        {
                            final Status status = locationSettingsResult.getStatus();
                            switch(status.getStatusCode())
                            {
                                case LocationSettingsStatusCodes.SUCCESS:
                                {
                                    Timber.d("All location settings are satisfied.");
                                    //noinspection MissingPermission
                                    LocationServices.FusedLocationApi.requestLocationUpdates(LocationService.this.apiClient, LocationService.this.locationRequest, listener);
                                    Dashboard.this.service.isAlreadyRequested = true;
                                    break;
                                }
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                {
                                    Timber.d("Location settings are not satisfied. Attempting to upgrade " +
                                            "location settings ");
                                    try
                                    {
                                        /* Show the dialog by calling startResolutionForResult(), and check the
                                         result in onActivityResult(). */
                                        status.startResolutionForResult(Dashboard.this, Dashboard.REQUEST_CHECK_SETTINGS);
                                    }
                                    catch(IntentSender.SendIntentException e)
                                    {
                                        Timber.e(e);
                                    }
                                }
                                break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                {
                                    String errorMessage = "Location settings are inadequate, and cannot be fixed here. Fix in Settings.";
                                    Timber.e(errorMessage);
                                    Toast.makeText(Dashboard.this, errorMessage, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }

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
                LocationServices.FusedLocationApi.removeLocationUpdates(
                        this.apiClient,
                        listener
                ).setResultCallback(new ResultCallback<Status>()
                {
                    @Override
                    public void onResult(@NonNull Status status)
                    {
                        LocationService.this.isAlreadyRequested = false;
                    }
                });
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

            this.availability.setBool(true);
            try
            {
                LocationServices.FusedLocationApi.flushLocations(this.apiClient);
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

            this.availability.setBool(false);
        }

        @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
        {
            Timber.d("onConnectionFailed");
            Toast.makeText(Dashboard.this, Dashboard.super.getResources().getString(R.string.error_connect_to_location_api), Toast.LENGTH_SHORT).show();

            this.availability.setBool(false);
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

        public void destroyService()
        {
            this.apiClient.disconnect();
        }
    }
}
