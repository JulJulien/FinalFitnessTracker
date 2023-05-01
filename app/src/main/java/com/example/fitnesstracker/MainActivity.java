package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ft_menu);

        Button MenuWorkoutButton = findViewById(R.id.MenuWorkoutButton);
        Button MenuWaterCalButton = findViewById(R.id.MenuWaterCalButton);
        Button MenuProgressButton = findViewById(R.id.MenuProgressButton);

        Intent workoutIntent = new Intent(this, TrackWorkoutActivity.class);
        Intent watercalIntent = new Intent(this, TrackWaterCalActivity.class);
        Intent progressIntent = new Intent(this, TrackProgressActivity.class);

        MenuWorkoutButton.setOnClickListener(v->
        {
            startActivity(workoutIntent);
        });

        MenuWaterCalButton.setOnClickListener(v->
        {
            startActivity(watercalIntent);
        });

        MenuProgressButton.setOnClickListener(v->
        {
            startActivity(progressIntent);
        });
    }
}
