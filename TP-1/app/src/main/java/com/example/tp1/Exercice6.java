package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Exercice6 extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor proximity;
    private ImageView pres;
    private ImageView loin;

    private float prox;

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            // La valeur de la lumière
            prox = sensorEvent.values[0];

            if (prox < 1) {
                pres.setVisibility(View.VISIBLE);
                loin.setVisibility(View.GONE);
            } else {
                pres.setVisibility(View.GONE);
                loin.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice6);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pres = findViewById(R.id.pres);
        loin = findViewById(R.id.loin);

        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(proximity != null) {
            sensorManager.registerListener(mSensorEventListener, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(mSensorEventListener);
    }
}
