package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Exercice2 extends AppCompatActivity {
    private LocationManager locationManager;
    private SensorManager sensorManager;
    private Sensor stepCounter;

    private TextView longitudeG;
    private TextView latitudeG;
    private TextView distanceG;
    private TextView distanceC;

    private Location firstLocation;
    private Location secondLocation;

    private long steps = 0;

    final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            secondLocation = location;
            float distance = firstLocation.distanceTo(secondLocation);

            latitudeG.setText("Latitude: " + location.getLatitude());
            longitudeG.setText("Longitude: " + location.getLongitude());
            distanceG.setText("Distance parcourue: " + distance + " m");

            firstLocation = location;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float distance = (float)(steps*78)/(float)100;
            distanceC.setText("Pas: " + steps +"\nDitance parcourue: " + distance + " m");
            steps++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice2);

        /* GPS */
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        firstLocation = new Location("gps");
        secondLocation = new Location("gps");

        latitudeG = findViewById(R.id.latitudeG);
        longitudeG = findViewById(R.id.longitudeG);
        distanceG = findViewById(R.id.distanceG);


        /* PODOMETRE */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        distanceC = findViewById(R.id.distanceC);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (locationManager != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else {
                Location locTemp = locationManager.getLastKnownLocation("gps");
                firstLocation.setLatitude(locTemp.getLatitude());
                firstLocation.setLongitude(locTemp.getLongitude());

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
            }
        }

        if(stepCounter != null) {
            sensorManager.registerListener(mSensorEventListener, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
        sensorManager.unregisterListener(mSensorEventListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    startActivity(getIntent());
                } else {
                }
                return;
            }

        }
    }

}
