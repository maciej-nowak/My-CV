package pl.maciej_nowak.mycv.navigate.location;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.squareup.otto.Produce;

/**
 * Created by Maciej on 19.06.2017.
 */

public class GPSService extends Service {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private NewLocationEvent location;

    public GPSService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupLocationListener();
        setupLocationManager();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPermissionDenied()) return;
        locationManager.removeUpdates(locationListener);
        BusProvider.getInstance().unregister(this);
    }

    private void setupLocationManager() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (isPermissionDenied()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private boolean isPermissionDenied() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;
    }

    private void setupLocationListener() {
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateLocation(new NewLocationEvent(location));
                BusProvider.getInstance().post(GPSService.this.location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {
            }
            public void onProviderDisabled(String provider) {
            }
        };
    }

    private void updateLocation(NewLocationEvent location) {
        this.location = location;
    }

    @Produce
    public NewLocationEvent produceNewLocation() {
        return location;
    }
}
