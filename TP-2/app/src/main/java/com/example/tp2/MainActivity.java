package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exo1(View view) {
        startActivity(new Intent(this, Exercice1.class));
    }

    public void exo2(View view) {
        startActivity(new Intent(this, Exercice2.class));
    }

    public void exo3(View view) {
        startActivity(new Intent(this, Exercice3.class));
    }

    public void exo4(View view) {
        startActivity(new Intent(this, Exercice4.class));
    }
}
