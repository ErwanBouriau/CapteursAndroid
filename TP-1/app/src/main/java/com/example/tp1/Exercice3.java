package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Exercice3 extends AppCompatActivity {
    private SensorManager sensorManager;
    private LinearLayout linear;

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            // Movement
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float acceleration = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (acceleration <= 1.5) {
                linear.setBackgroundColor(Color.GREEN);
            }
            else if (acceleration <= 3) {
                linear.setBackgroundColor(Color.BLACK);
            }
            else if (acceleration <= 5) {
                linear.setBackgroundColor(Color.RED);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice3);
        linear = findViewById(R.id.linear3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometre != null) {
            sensorManager.registerListener(mSensorEventListener, accelerometre, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
