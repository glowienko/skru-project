package project.skru_dane_po_warszawsku.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.Date;
import java.util.Objects;

public class CurrentLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final Integer REQUEST_CHECK_SETTINGS = 0x1;
    private static final String LAST_UPDATED_TIME_STRING_KEY = "last_updated";
    private static final String LOCATION_KEY = "location";
    private static final String REQUESTING_LOCATION_UPDATES_KEY = "request_location_update";


    private int LOCATION_PERMISSION_REQUEST_CODE;
    private GoogleApiClient googleApiClient;
    private Location bestLocation;
    private LocationRequest locationRequest;
    private String lastUpdateTime;
    private Context mContext;
    private boolean requestingLocationUpdates;


    public CurrentLocation(Context context) {
        mContext = context;
        ActivityCompat.requestPermissions((Activity) mContext, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }

    public void onRequestPermissionResult(int requestCode, String[] permission, int[] grantResult) {
        if (permission.length == 1 && Objects.equals(permission[0], Manifest.permission.ACCESS_FINE_LOCATION)
                && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
            //why is it empty ? :o
        }
    }

    public synchronized GoogleApiClient buildGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        return googleApiClient;
    }

    public void startLocationUpdates() {

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && requestingLocationUpdates) {
            requestingLocationUpdates = false;
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    public void stopLocationUpdates() {
        requestingLocationUpdates = true;
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }

    public void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        final PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates states = result.getLocationSettingsStates();

                switch (status.getStatusCode()) {

                    case LocationSettingsStatusCodes.SUCCESS:
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult((Activity) mContext, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLocationChanged(Location location) {
        bestLocation = location;
        lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
    }

    public Location getLocation() {
        return bestLocation;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, requestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, bestLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, lastUpdateTime);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                bestLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                locationRequest = new LocationRequest();
            }
            startLocationUpdates();

        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}