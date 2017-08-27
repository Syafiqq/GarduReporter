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
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
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
import java.util.Observer;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class GPSApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
    // Used in checking for runtime permissions.
    public static final int REQUEST_PERMISSIONS_REQUEST_CODE = 0x22;
    public static final int REQUEST_CHECK_SETTINGS = 0x01;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 3500;

    private Activity activity;

    private BooleanObserver availability;
    private ObservableLocation location;
    private GoogleApiClient apiClient;
    private LocationRequest locationRequest;
    private LocationManager locationManager;
    private LocationSettingsRequest locationSettingsRequest;

    private boolean isAlreadyRequested;

    public GPSApi(Activity activity)
    {
        Timber.d("Constructor");
        this.availability = new BooleanObserver(false);
        this.isAlreadyRequested = false;
        this.activity = activity;
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

        this.availability.setBool(false);
        Toast.makeText(GPSApi.this.activity, GPSApi.this.activity.getResources().getString(R.string.error_connect_to_location_api), Toast.LENGTH_SHORT).show();
    }

    @Override public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");

        if(location != null)
        {
            this.location.setLocation(location);
        }
    }

    //==============================================================================================

    public void initializeService()
    {
        Timber.d("initializeService");

        this.createAPI();
        this.createLocationRequest();
        this.createLocationManager();
        this.buildLocationSettingsRequest();
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

    private void createLocationRequest()
    {
        Timber.d("createLocationRequest");

        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void createLocationManager()
    {
        Timber.d("createLocationManager");

        this.locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
    }

    public void buildLocationSettingsRequest()
    {
        Timber.d("buildLocationSettingsRequest");

        final LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);
        this.locationSettingsRequest = builder.build();
    }

    public void requestLocationUpdates(final LocationListener listener)
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
                                Timber.d("All location settings are satisfied.");
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
                                Timber.d("Location settings are not satisfied. Attempting to upgrade location settings ");
                                try
                                {
                                    status.startResolutionForResult(GPSApi.this.activity, GPSApi.REQUEST_CHECK_SETTINGS);
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
                    GPSApi.this.isAlreadyRequested = false;
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
        @NotNull final Bundle bundle = new Bundle();
        this.locationManager.sendExtraCommand(LocationManager.GPS_PROVIDER, "delete_aiding_data", null);
        this.locationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
        this.locationManager.sendExtraCommand("gps", "force_time_injection", bundle);
    }

    public void destroyService()
    {
        this.apiClient.disconnect();
    }

    private boolean checkPermissions()
    {
        Timber.d("checkPermissions");

        return (ActivityCompat.checkSelfPermission(GPSApi.this.activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(GPSApi.this.activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermissions(int rootID)
    {
        Timber.d("requestPermissions");

        final boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this.activity, Manifest.permission.ACCESS_FINE_LOCATION);

        /* Provide an additional rationale to the user. This would happen if the user denied the
         request previously, but didn't check the "Don't ask again" checkbox.*/
        if(shouldProvideRationale)
        {
            Timber.d("Displaying permission rationale to provide additional context.");

            Snackbar.make(
                    this.activity.findViewById(rootID),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.command_ok, new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            ActivityCompat.requestPermissions(GPSApi.this.activity,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        }
        else
        {
            Timber.d("Requesting permission");
            /* Request permission. It's possible this can be auto answered if device policy
             sets the permission in a given state or the user denied the permission
             previously and checked "Never ask again".*/
            ActivityCompat.requestPermissions(GPSApi.this.activity,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    public BooleanObserver getAvailability()
    {
        return this.availability;
    }

    public void setAvailability(BooleanObserver availability)
    {
        this.availability = availability;
    }

    public ObservableLocation getLocation()
    {
        return this.location;
    }

    public void setLocation(ObservableLocation location)
    {
        this.location = location;
    }

    public boolean isAlreadyRequested()
    {
        return this.isAlreadyRequested;
    }

    public void setAlreadyRequested(boolean alreadyRequested)
    {
        this.isAlreadyRequested = alreadyRequested;
    }

    public void addObserver(Observer o)
    {
        this.location.addObserver(o);
    }

    public void deleteObserver(Observer o)
    {
        this.location.deleteObserver(o);
    }
}
