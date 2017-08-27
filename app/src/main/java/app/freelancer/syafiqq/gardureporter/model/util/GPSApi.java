package app.freelancer.syafiqq.gardureporter.model.util;

/*
 * This <android-gardu-reporter> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2017, 8:00 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.custom.android.location.BooleanObserver;
import app.freelancer.syafiqq.gardureporter.model.custom.android.location.ObservableLocation;
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
import timber.log.Timber;

public class GPSApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
    // Used in checking for runtime permissions.
    public static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;
    public static final int SECONDS_DELAYED = 45;
    public static final int REQUEST_CHECK_SETTINGS = 0x01;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 3500;
    public static final float COUNT_REQUEST_THRESHOLD = 7;
    private static final String TAG = GPSApi.class.getSimpleName();
    public BooleanObserver availability;
    protected GoogleApiClient apiClient;
    protected LocationRequest locationRequest;
    protected LocationSettingsRequest locationSettingsRequest;
    protected Location current;
    private Activity activity;
    private ObservableLocation oLocation;
    private boolean isAlreadyRequested;
    private LocationManager locationManager;

    public GPSApi(Activity activity)
    {
        Timber.d("Constructor");
        this.availability = new BooleanObserver(false);
        this.isAlreadyRequested = false;
        this.activity = activity;
    }

    public void initializeService()
    {
        Timber.d("initializeService");

        this.createAPI();
        this.createLocationRequest();
        this.createLocationManager();
        this.buildLocationSettingsRequest();
    }

    public void buildLocationSettingsRequest()
    {
        final LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);
        this.locationSettingsRequest = builder.build();
    }

    public void requestLocationUpdates(final LocationListener listener, final Activity activity)
    {
        Timber.d("requestLocationUpdates");

        this.resetGPS();
        try
        {
            if(!this.isAlreadyRequested)
            {
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
                                if(ActivityCompat.checkSelfPermission(GPSApi.this.activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GPSApi.this.activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                                {
                                    return;
                                }
                                LocationServices.FusedLocationApi.requestLocationUpdates(GPSApi.this.apiClient, GPSApi.this.locationRequest, listener);
                                GPSApi.this.isAlreadyRequested = true;
                                break;
                            }
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            {
                                Timber.d("Location settings are not satisfied. Attempting to upgrade oLocation settings ");
                                try
                                {
                                    status.startResolutionForResult(activity, GPSApi.REQUEST_CHECK_SETTINGS);
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
                                Toast.makeText(GPSApi.this.activity, errorMessage, Toast.LENGTH_LONG).show();
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
                    GPSApi.this.isAlreadyRequested = false;
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
        this.locationManager.sendExtraCommand(LocationManager.GPS_PROVIDER, "delete_aiding_data", null);
        Bundle bundle = new Bundle();
        this.locationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
        this.locationManager.sendExtraCommand("gps", "force_time_injection", bundle);
    }

    private void createLocationManager()
    {
        Timber.d("createLocationManager");

        this.locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
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

        this.apiClient = new GoogleApiClient.Builder(GPSApi.this.activity)
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
        Toast.makeText(GPSApi.this.activity, GPSApi.this.activity.getResources().getString(R.string.error_connect_to_location_api), Toast.LENGTH_SHORT).show();

        this.availability.setBool(false);
    }

    public void destroyService()
    {
        this.apiClient.disconnect();
    }

    @Override public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");

        if(location != null)
        {
            this.oLocation.setLocation(location);
        }
    }
}
