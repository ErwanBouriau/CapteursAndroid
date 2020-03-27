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
import android.widget.TextView;

public class Exercice4 extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometre;
    private TextView textView1;
    private TextView textView2;

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            // Movement
            float x = values[0];
            float y = values[1];

            if (x > 0) {
                textView1.setText("gauche");
            }
            if (x < 0) {
                textView1.setText("droite");
            }
            if (y < 0) {
                textView2.setText("bas");
            }
            if (y > 0) {
                textView2.setText("haut");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice4);

        textView1 = findViewById(R.id.direction_x);
        textView2 = findViewById(R.id.direction_y);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(accelerometre != null) {
            sensorManager.registerListener(mSensorEventListener, accelerometre, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(mSensorEventListener);
    }
}
