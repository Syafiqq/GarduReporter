package app.freelancer.syafiqq.gardureporter.model.service;

/*
  This <GarduReporter> project created by :
  Name         : syafiq
  Date / Time  : 04 June 2017, 2:34 PM.
  Email        : syafiq.rezpector@gmail.com
  Github       : syafiqq
 */

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import app.freelancer.syafiqq.gardureporter.model.util.Utils;
import timber.log.Timber;

/**
 * A bound and started service that is promoted to a foreground service when location updates have
 * been requested and all clients unbind.
 * <p>
 * For apps running in the background on "O" devices, location is computed only once every 10
 * minutes and delivered batched every 30 minutes. This restriction applies even to apps
 * targeting "N" or lower which are run on "O" devices.
 * <p>
 * This sample show how to use a long-running service for location updates. When an activity is
 * bound to this service, frequent location updates are permitted. When the activity is removed
 * from the foreground, the service promotes itself to a foreground service, and location updates
 * continue. When the activity comes back to the foreground, the foreground service stops, and the
 * notification assocaited with that service is removed.
 */
public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks,
                                                        GoogleApiClient.OnConnectionFailedListener,
                                                        LocationListener
{
    public static final String PACKAGE_NAME = LocationService.class.getPackage().getName();
    public static final String ACTION_BROADCAST = PACKAGE_NAME + ".broadcast";
    public static final String EXTRA_LOCATION = PACKAGE_NAME + ".location";

    private static final String TAG = LocationService.class.getSimpleName();

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 50000;
    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 30000;
    /**
     * The identifier for the notification displayed for the foreground service.
     */
    private final IBinder mBinder = new LocalBinder();

    /**
     * The entry point to Google Play Services.
     */
    private GoogleApiClient apiClient;

    /**
     * Contains parameters used by {@link com.google.android.gms.location.FusedLocationProviderApi}.
     */
    private LocationRequest locationRequest;

    private Handler serviceHandler;

    /**
     * The current location.
     */
    private Location location;

    public LocationService()
    {
    }

    @Override
    public void onCreate()
    {
        Timber.d("onCreate");

        this.apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        this.apiClient.connect();
        this.createLocationRequest();

        final HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.serviceHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Timber.d("onStartCommand");

        // Tells the system to not try to recreate the service after it has been killed.
        return START_NOT_STICKY;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Timber.d("onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // Called when a client (MainActivity in case of this sample) comes to the foreground
        // and binds with this service. The service should cease to be a foreground service
        // when that happens.
        Timber.d("onBind");

        super.stopForeground(true);
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent)
    {
        // Called when a client (MainActivity in case of this sample) returns to the foreground
        // and binds once again with this service. The service should cease to be a foreground
        // service when that happens.
        Timber.d("onRebind");

        super.stopForeground(true);
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Timber.d("onUnbind");

        return true; // Ensures onRebind() is called when a client re-binds.
    }

    @Override
    public void onDestroy()
    {
        Timber.d("onDestroy");

        this.serviceHandler.removeCallbacksAndMessages(null);
        this.apiClient.disconnect();
    }

    /**
     * Makes a request for location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void requestLocationUpdates()
    {
        Timber.d("requestLocationUpdates");

        Utils.setRequestingLocationUpdates(this, true);
        this.startService(new Intent(getApplicationContext(), LocationService.class));
        try
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.apiClient, this.locationRequest, LocationService.this);
        }
        catch(SecurityException unlikely)
        {
            Utils.setRequestingLocationUpdates(this, false);
            Timber.e("Lost location permission. Could not request updates. " + unlikely);
        }
    }

    /**
     * Removes location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void removeLocationUpdates()
    {
        Timber.d("removeLocationUpdates");

        try
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.apiClient, LocationService.this);
            Utils.setRequestingLocationUpdates(this, false);
            super.stopSelf();
        }
        catch(SecurityException unlikely)
        {
            Utils.setRequestingLocationUpdates(this, true);
            Timber.e("Lost location permission. Could not remove updates. " + unlikely);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        Timber.d("onConnected");

        try
        {
            this.location = LocationServices.FusedLocationApi.getLastLocation(this.apiClient);
        }
        catch(SecurityException unlikely)
        {
            Timber.e("Lost location permission." + unlikely);
        }
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        // In this example, we merely log the suspension.
        Timber.e("GoogleApiClient connection suspended.");
    }

    @Override
    public void onConnectionFailed(@NotNull ConnectionResult connectionResult)
    {
        // In this example, we merely log the failure.
        Timber.e("GoogleApiClient connection failed.");
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Timber.d("onLocationChanged");

        this.location = location;

        // Notify anyone listening for broadcasts about the new location.
        Intent intent = new Intent(ACTION_BROADCAST);
        intent.putExtra(EXTRA_LOCATION, location);
        LocalBroadcastManager.getInstance(super.getApplicationContext()).sendBroadcast(intent);
    }

    /**
     * Sets the location request parameters.
     */
    private void createLocationRequest()
    {
        Timber.d("createLocationRequest");

        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Class used for the client Binder.  Since this service runs in the same process as its
     * clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder
    {
        public LocationService getService()
        {
            Timber.d("getService");

            return LocationService.this;
        }
    }
}
