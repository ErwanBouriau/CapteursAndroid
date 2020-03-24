package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Exercice2 extends AppCompatActivity {
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice2);

        TextView sensorTemp = findViewById(R.id.text1);
        TextView sensorAccele = findViewById(R.id.text2);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensorTemp.setText("Capteur de température fonctionnel");
        } else {
            sensorTemp.setText("Pas de capteur de température");
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensorAccele.setText("Capteur d'accélération fonctionnel");
        } else {
            sensorAccele.setText("Pas de capteur d'accélération");
        }
    }
}
