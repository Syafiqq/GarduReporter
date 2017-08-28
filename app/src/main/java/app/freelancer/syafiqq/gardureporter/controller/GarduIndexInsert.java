package app.freelancer.syafiqq.gardureporter.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndexOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndukOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduPenyulangOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.JenisGarduOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.LocationOrm;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

public class GarduIndexInsert extends AppCompatActivity implements View.OnClickListener, LocationListener, OnMapReadyCallback
{
    private static final String TAG = GarduIndexInsert.class.getSimpleName();

    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;
    private static final int SECONDS_DELAYED = 45;
    private static final int REQUEST_CHECK_SETTINGS = 0x01;

    private LocationService service;
    private ObservableLocation oLocation;

    // UI
    private ProgressBar progress;

    private SearchableSpinner garduInduk;
    private SearchableSpinner garduPenyulang;
    private Spinner jenisGardu;
    private Switch location;
    private TextInputEditText noGardu;
    private TextInputEditText alamat;
    private TextInputEditText merk;
    private TextInputEditText serial;
    private TextInputEditText daya;
    private TextInputEditText fasa;
    private TextInputEditText tap;
    private TextInputEditText jurusan;
    private Button submit;

    //ORM
    private GarduIndexOrm report;

    //Observer
    private Observer accuracyObserver;
    private Observer serviceObserver;
    private Handler handler;
    private Runnable asyncOverrideLocationRequest;
    private boolean isSubmitRequested;

    private int countRequest;
    private GoogleMap map;
    private Marker marker;
    private LinearLayout mapContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timber.d("onCreate");

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_dashboard);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_dashboard_toolbar_toolbar);
        super.setSupportActionBar(toolbar);

        this.service = new LocationService();
        this.report = new GarduIndexOrm();
        this.oLocation = new ObservableLocation(null);
        this.handler = new Handler();

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

        this.garduInduk = (SearchableSpinner) findViewById(R.id.content_dashboard_searchablespinner_induk);
        this.garduPenyulang = (SearchableSpinner) findViewById(R.id.content_dashboard_searchablespinner_penyulang);
        this.jenisGardu = (Spinner) findViewById(R.id.content_dashboard_spinner_jenisgardu);
        this.location = (Switch) findViewById(R.id.content_dashboard_switch_location);
        this.noGardu = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_nogardu);
        this.alamat = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_alamat);
        this.merk = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_merk);
        this.serial = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_serial);
        this.daya = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_daya);
        this.fasa = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_fasa);
        this.tap = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_tap);
        this.jurusan = (TextInputEditText) findViewById(R.id.content_dashboard_edittext_jurusan);
        this.submit = (Button) findViewById(R.id.content_dashboard_button_submit);
        this.mapContainer = (LinearLayout) findViewById(R.id.content_dashboard_linearlayout_map_container);
        final SupportMapFragment mapFragment = (SupportMapFragment) super.getSupportFragmentManager().findFragmentById(R.id.content_dashboard_fragment_map);

        this.progress = (ProgressBar) findViewById(R.id.content_dashboard_progress_bar_submit);
        final ImageButton garduIndukSync = (ImageButton) findViewById(R.id.content_dashboard_imagebutton_induk_refresh);
        final ImageButton garduPenyulangSync = (ImageButton) findViewById(R.id.content_dashboard_imagebutton_penyulang_refresh);


        mapFragment.getMapAsync(this);

        this.isSubmitRequested = false;
        this.countRequest = 0;

        this.updateAccuracy(this.oLocation.getLocation());

        this.accuracyObserver = new Observer()
        {
            @Override public void update(Observable observable, Object o)
            {
                GarduIndexInsert.this.updateAccuracy((Location) o);
                GarduIndexInsert.this.updateMap(GarduIndexInsert.this.map, (Location) o);
            }
        };
        this.serviceObserver = new Observer()
        {
            @Override public void update(Observable observable, Object o)
            {
                BooleanObserver bool = (BooleanObserver) observable;
                final boolean resultedPermission = GarduIndexInsert.this.checkPermissions() && bool.isBool();
                GarduIndexInsert.this.shiftUISubmit(resultedPermission);
            }
        };
        this.asyncOverrideLocationRequest = new Runnable()
        {
            @Override public void run()
            {
                Timber.d("asyncOverrideLocationRequest");

                @Nullable final Location location = GarduIndexInsert.this.oLocation.getLocation();
                if(location == null)
                {
                    Toast.makeText(GarduIndexInsert.this, GarduIndexInsert.super.getResources().getString(R.string.error_no_location_retrieved), Toast.LENGTH_SHORT).show();
                    GarduIndexInsert.this.isSubmitRequested = false;
                    //GarduIndexInsert.this.service.removeLocationUpdates(GarduIndexInsert.this);
                    GarduIndexInsert.this.shiftUISubmit(true);
                }
                else
                {
                    GarduIndexInsert.this.prepareSubmit(location);
                }
            }
        };

        this.submit.setOnClickListener(this);
        this.location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                {
                    GarduIndexInsert.this.onLocationRequestSwitchedOn();
                }
                else
                {
                    GarduIndexInsert.this.onLocationRequestSwitchedOff();
                }
            }
        });
        this.oLocation.addObserver(this.accuracyObserver);
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
                GarduDao.findAllInduk(GarduIndexInsert.super.getApplicationContext(), new GarduDao.GarduResponseListener<List<GarduIndukOrm>>()
                {
                    @Override public void onResponseFailed(int status, String message)
                    {
                        Timber.d("onResponseFailed");

                        if(message != null)
                        {
                            Toast.makeText(GarduIndexInsert.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onResponseSuccessful(List<GarduIndukOrm> gardu, int status, String message)
                    {
                        Timber.d("onResponseSuccessful");

                        ((GarduIndukAdapter) garduIndukAdapter).update(gardu);
                        garduIndukAdapter.notifyDataSetChanged();
                        this.onResponseFailed(status, message);
                    }
                });
            }
        });
        garduPenyulangSync.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                GarduDao.findAllPenyulang(GarduIndexInsert.super.getApplicationContext(), new GarduDao.GarduResponseListener<List<GarduPenyulangOrm>>()
                {
                    @Override public void onResponseFailed(int status, String message)
                    {
                        Timber.d("onResponseFailed");

                        if(message != null)
                        {
                            Toast.makeText(GarduIndexInsert.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override public void onResponseSuccessful(List<GarduPenyulangOrm> gardu, int status, String message)
                    {
                        Timber.d("onResponseSuccessful");

                        ((GarduPenyulangAdapter) garduPenyulangAdapter).update(gardu);
                        garduPenyulangAdapter.notifyDataSetChanged();
                        this.onResponseFailed(status, message);
                    }
                });
            }
        });
        garduIndukSync.callOnClick();
        garduPenyulangSync.callOnClick();
        this.location.setChecked(false);
    }

    private void updateMap(GoogleMap map, Location o)
    {
        if(map != null)
        {
            if(o == null)
            {
                if(this.marker != null)
                {
                    this.marker.setVisible(false);
                }
            }
            else
            {
                LatLng latLng = new LatLng(o.getLatitude(), o.getLongitude());

                if(this.marker != null)
                {
                    this.marker.setPosition(latLng);
                }
                else
                {
                    this.marker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
                this.marker.setVisible(true);
            }
        }
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

        this.oLocation.deleteObserver(this.accuracyObserver);
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
            {
                TokenDao.logoutAccount(this);
                return true;
            }
            case R.id.activity_dashboard_menu_jump_to_gardu_index_measurement:
            {
                @NotNull Intent intent = new Intent(GarduIndexInsert.this, GarduIndexMeasurement.class);
                super.startActivity(intent);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case GarduIndexInsert.REQUEST_CHECK_SETTINGS:
            {
                switch(resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        Timber.d("User agreed to make required oLocation settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                    }
                    break;
                    case Activity.RESULT_CANCELED:
                    {
                        Timber.d("User chose not to make required oLocation settings changes.");
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

        this.location.setText(super.getResources().getString(R.string.content_dashboard_accuracy_label));
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
            if(!this.service.isAlreadyRequested)
            {
                this.countRequest = 0;
                this.oLocation.setLocation(null);
                this.service.requestLocationUpdates(this);
            }
        }
        this.mapContainer.setVisibility(View.VISIBLE);
    }

    private void onLocationRequestSwitchedOff()
    {
        Timber.d("onLocationRequestSwitchedOff");

        this.shiftUISubmit(true);
        this.location.setChecked(false);
        this.service.removeLocationUpdates(GarduIndexInsert.this);
        this.oLocation.setLocation(null);
        this.updateMap(this.map, null);
        this.mapContainer.setVisibility(View.GONE);
    }


    /*
     * Returns the tap state of the permissions needed.
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
                            ActivityCompat.requestPermissions(GarduIndexInsert.this,
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
            ActivityCompat.requestPermissions(GarduIndexInsert.this,
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

        final GarduIndexOrm report = this.report;
        report.setGarduInduk((GarduIndukOrm) this.garduInduk.getSelectedItem());
        report.setGarduPenyulang((GarduPenyulangOrm) this.garduPenyulang.getSelectedItem());
        report.setJenis((JenisGarduOrm) this.jenisGardu.getSelectedItem());
        report.setNo(this.noGardu.getText().toString());
        report.setAlamat(this.alamat.getText().toString());
        report.setMerk(this.merk.getText().toString());
        report.setSerial(this.serial.getText().toString());
        if(TextUtils.isEmpty(this.daya.getText()))
        {
            report.setDaya(null);
        }
        else
        {
            report.setDaya(Integer.parseInt(this.daya.getText().toString()));
        }
        if(TextUtils.isEmpty(this.tap.getText()))
        {
            report.setTap(null);
        }
        else
        {
            report.setTap(Integer.parseInt(this.tap.getText().toString()));
        }
        if(TextUtils.isEmpty(this.jurusan.getText()))
        {
            report.setJurusan(null);
        }
        else
        {
            report.setJurusan(Integer.parseInt(this.jurusan.getText().toString()));
        }
        report.setFasa(this.fasa.getText().toString());

        if(!checkPermissions())
        {
            requestPermissions();
        }
        else
        {
            if(!this.location.isChecked())
            {
                this.location.setChecked(true);
            }
            else
            {
                this.onLocationRequestSwitchedOn();
            }
            this.isSubmitRequested = true;
            this.handler.postDelayed(this.asyncOverrideLocationRequest, SECONDS_DELAYED * 1000);
        }
    }

    @Override public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");

        if(location != null)
        {
            Timber.d("Location [%b] [%.14g,%.14g] [%s]", this.oLocation.getLocation() != null, location.getLatitude(), location.getLongitude(), this.countRequest);
            this.oLocation.setLocation(location);

            if(this.isSubmitRequested)
            {
                if(this.countRequest >= LocationService.COUNT_REQUEST_THRESHOLD)
                {
                    this.prepareSubmit(this.oLocation.getLocation());
                }
            }
            ++this.countRequest;
        }
    }

    private void prepareSubmit(@NotNull Location location)
    {
        Timber.d("prepareSubmit");

        this.handler.removeCallbacks(this.asyncOverrideLocationRequest);
        this.isSubmitRequested = false;
        //this.service.removeLocationUpdates(this);

        final GarduIndexOrm report = GarduIndexInsert.this.report;
        if((report.getLatitude() == null) || (report.getLongitude() == null))
        {
            report.setLocation(new LocationOrm(location));
        }

        this.doSubmit(GarduIndexInsert.this.report);
    }

    private void doSubmit(final GarduIndexOrm report)
    {
        Timber.d("doSumbit");

        GarduDao.sendGardu(super.getApplicationContext(), report, new GarduDao.GarduRequestListener()
        {
            @Override public void onRequestFailed(int status, String message)
            {
                Timber.d("onRequestFailed");

                if(message != null)
                {
                    Toast.makeText(GarduIndexInsert.this, message, Toast.LENGTH_SHORT).show();
                }
                GarduIndexInsert.this.shiftUISubmit(true);

            }

            @Override public void onRequestSuccessful(int status, String message)
            {
                Timber.d("onRequestSuccessful");

                this.onRequestFailed(status, message);
                GarduIndexInsert.this.location.setChecked(false);
                GarduIndexInsert.this.cleanForm();
            }
        });
    }

    private void shiftUISubmit(boolean finished)
    {
        if(finished)
        {
            this.progress.setVisibility(View.GONE);
            this.submit.setEnabled(true);
            this.location.setEnabled(true);
        }
        else
        {
            this.progress.setVisibility(View.VISIBLE);
            this.submit.setEnabled(false);
            this.location.setEnabled(false);
        }
    }

    private void cleanForm()
    {
        this.noGardu.getText().clear();
        this.alamat.getText().clear();
        this.merk.getText().clear();
        this.serial.getText().clear();
        this.daya.getText().clear();
        this.fasa.getText().clear();
        this.tap.getText().clear();
        this.jurusan.getText().clear();
    }

    @Override public void onMapReady(GoogleMap googleMap)
    {
        //LatLng sydney = new LatLng(-33.852, 151.211);
        //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMinZoomPreference(17.0f);
        googleMap.setMaxZoomPreference(17.0f);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        this.map = googleMap;
    }

    private final class LocationService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    {
        /**
         * The desired interval for oLocation updates. Inexact. Updates may be more or less frequent.
         */
        private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
        /**
         * The fastest rate for active oLocation updates. Updates will never be more frequent
         * than this value.
         */
        private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 3500;

        private static final float COUNT_REQUEST_THRESHOLD = 7;
        public BooleanObserver availability;
        /**
         * Provides the entry point to Google Play services.
         */
        protected GoogleApiClient apiClient;
        /**
         * Stores parameters for requests to the FusedLocationProviderApi.
         */
        protected LocationRequest locationRequest;
        /**
         * Stores the types of oLocation services the client is interested in using. Used for checking
         * settings to determine if the device has optimal oLocation settings.
         */
        protected LocationSettingsRequest locationSettingsRequest;
        /**
         * Represents a geographical oLocation.
         */
        protected Location mCurrentLocation;
        private boolean isAlreadyRequested;
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
         * if a device has the needed oLocation settings.
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
                                    Timber.d("All oLocation settings are satisfied.");
                                    //noinspection MissingPermission
                                    LocationServices.FusedLocationApi.requestLocationUpdates(LocationService.this.apiClient, LocationService.this.locationRequest, listener);
                                    GarduIndexInsert.this.service.isAlreadyRequested = true;
                                    break;
                                }
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                {
                                    Timber.d("Location settings are not satisfied. Attempting to upgrade " +
                                            "oLocation settings ");
                                    try
                                    {
                                        /* Show the dialog by calling startResolutionForResult(), and check the
                                         result in onActivityResult(). */
                                        status.startResolutionForResult(GarduIndexInsert.this, GarduIndexInsert.REQUEST_CHECK_SETTINGS);
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
                                    Toast.makeText(GarduIndexInsert.this, errorMessage, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }

            }
            catch(SecurityException unlikely)
            {
                Timber.e("Lost oLocation permission. Could not request updates. " + unlikely);
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
                Timber.e("Lost oLocation permission. Could not remove updates. " + unlikely);
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

            this.locationManager = (LocationManager) GarduIndexInsert.super.getSystemService(LOCATION_SERVICE);
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

            this.apiClient = new GoogleApiClient.Builder(GarduIndexInsert.this)
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
                Timber.e("Lost oLocation permission." + unlikely);
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
            Toast.makeText(GarduIndexInsert.this, GarduIndexInsert.super.getResources().getString(R.string.error_connect_to_location_api), Toast.LENGTH_SHORT).show();

            this.availability.setBool(false);
        }

        public void destroyService()
        {
            this.apiClient.disconnect();
        }
    }
}
