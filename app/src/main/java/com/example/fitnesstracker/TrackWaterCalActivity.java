package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TrackWaterCalActivity extends AppCompatActivity {

    private EditText editCaloriesText;
    private EditText editWaterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ft_trackwatercal);

        editCaloriesText = findViewById(R.id.CaloriesEditText);
        editWaterText = findViewById(R.id.WaterOzEditText);

        Button submitButton = findViewById(R.id.WaterCalSubmitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("fitnessData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                String caloriesText = editCaloriesText.getText().toString();
                String waterText = editWaterText.getText().toString();

                if (caloriesText.isEmpty() || waterText.isEmpty()) {
                    Toast.makeText(TrackWaterCalActivity.this, "Please fill all data entries!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int calories = Integer.parseInt(caloriesText);
                int water = Integer.parseInt(waterText);

                String waterCalData = "Calories: " + calories + "\nWater (oz): " + water + "\n\n";

                String existingWaterCalData = sharedPref.getString("waterCalData", "");
                editor.putString("waterCalData", existingWaterCalData + waterCalData);
                editor.apply();
                Toast.makeText(TrackWaterCalActivity.this, "Water and calorie data saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}