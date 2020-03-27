package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Exercice3 extends AppCompatActivity {
    private LocationManager locationManager;
    private TextView direction;


    final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location.getBearing() == 0) {
                direction.setText("Direction: Nord");
            }
            if (location.getBearing() > 0 && location.getBearing() < 90) {
                direction.setText("Direction: Nord-Est");
            }
            if (location.getBearing() == 90) {
                direction.setText("Direction: Est");
            }
            if (location.getBearing() > 90 && location.getBearing() < 180) {
                direction.setText("Direction: Sud-Est");
            }
            if (location.getBearing() == 180) {
                direction.setText("Direction: Sud");
            }
            if (location.getBearing() > 180 && location.getBearing() < 270) {
                direction.setText("Direction: Sud-Ouest");
            }
            if (location.getBearing() == 270) {
                direction.setText("Direction: Ouest");
            }
            if (location.getBearing() > 270) {
                direction.setText("Direction: Nord-Ouest");
            }

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice3);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        direction = findViewById(R.id.direction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationManager != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, locationListener);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

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

