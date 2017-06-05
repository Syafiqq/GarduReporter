package app.freelancer.syafiqq.gardureporter.controller.dump;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import app.freelancer.syafiqq.gardureporter.R;

public class Dashboard extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleApiClient.ConnectionCallbacks
{
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean error = false;
    private GoogleApiClient googleApi;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude;
    private double longitude;
    private LocationRequest locationRequest;

    public static boolean isInternetConnected(Context ctx)
    {
        final ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        @Nullable
        final NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && (activeNetwork.getState() == NetworkInfo.State.CONNECTED || activeNetwork.getState() == NetworkInfo.State.CONNECTING);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_dashboard_toolbar_toolbar);
        setSupportActionBar(toolbar);

        this.googleApi = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        this.locationRequest = new LocationRequest();
        locationRequest.setInterval(30000); //5 seconds
        locationRequest.setFastestInterval(30000); //3 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationManager.sendExtraCommand(LocationManager.GPS_PROVIDER, "delete_aiding_data", null);
        Bundle bundle = new Bundle();
        locationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
        locationManager.sendExtraCommand("gps", "force_time_injection", bundle);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        this.locationListener = this;
    }

    @Override
    public void onConnectionFailed(@NotNull ConnectionResult result)
    {
        if(!this.error)
        {
            if(result.hasResolution())
            {
                try
                {
                    this.error = true;
                    result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
                }
                catch(IntentSender.SendIntentException e)
                {
                    this.googleApi.connect();
                }
            }
            else
            {
                // Show dialog using GoogleApiAvailability.getErrorDialog()
                showErrorDialog(result.getErrorCode());
                error = true;
            }
        }
    }

    private void showErrorDialog(int errorCode)
    {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(super.getFragmentManager(), "errordialog");
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(isGPSEnabled(this))
        {
            if(locationListener != null)
            {
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }

                if(locationManager != null)
                {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null)
                    {
                        Dashboard.this.latitude = location.getLatitude();
                        Dashboard.this.longitude = location.getLongitude();
                        Log.d("Dashboard", "GPS Your Location is - \nLat: " + latitude + "\nLong: " + longitude);
                    }
                }
            }
        }

        if(isInternetConnected(this))
        {
            if(locationManager != null)
            {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(location != null)
                {
                    Dashboard.this.latitude = location.getLatitude();
                    Dashboard.this.longitude = location.getLongitude();
                    Log.d("Dashboard", "Network Your Location is - \nLat: " + latitude + "\nLong: " + longitude);
                }
            }
        }
    }

    protected void onStart()
    {
        this.googleApi.connect();
        super.onStart();
    }

    protected void onStop()
    {
        this.googleApi.disconnect();
        super.onStop();
    }

    public boolean isGPSEnabled(Context mContext)
    {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApi, locationRequest, this.locationListener);
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }
}
