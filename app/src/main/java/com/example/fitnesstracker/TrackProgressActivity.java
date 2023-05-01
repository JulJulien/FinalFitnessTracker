package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TrackProgressActivity extends AppCompatActivity {

    private TableLayout workoutDataTable;
    private TableLayout waterCalDataTable;
    private Button deleteWorkoutDataButton;
    private Button deleteWaterCalDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ft_myprogress);

        workoutDataTable = findViewById(R.id.workoutDataTable);
        waterCalDataTable = findViewById(R.id.waterCalDataTable);

        deleteWorkoutDataButton = findViewById(R.id.deleteWorkoutDataButton);
        deleteWaterCalDataButton = findViewById(R.id.deleteWaterCalDataButton);

        SharedPreferences sharedPref = getSharedPreferences("fitnessData", MODE_PRIVATE);

        String workoutData = sharedPref.getString("workoutData", "");
        String waterCalData = sharedPref.getString("waterCalData", "");

        populateWorkoutTable(workoutData);
        populateWaterCalTable(waterCalData);

        deleteWorkoutDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastWorkoutEntry();
            }
        });

        deleteWaterCalDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastWaterCalEntry();
            }
        });
    }

    // The rest of the existing methods

    private void populateWorkoutTable(String workoutData) {
        if (!workoutData.isEmpty()) {
            String[] workoutEntries = workoutData.split("\n\n");

            for (String entry : workoutEntries) {
                String[] entryValues = entry.split("\n");

                TableRow newRow = new TableRow(this);
                for (String value : entryValues) {
                    if (value.split(": ").length > 1) {
                        TextView cell = new TextView(this);
                        cell.setText(value.split(": ")[1]);
                        cell.setTextColor(Color.WHITE);
                        cell.setPadding(8, 8, 8, 8);
                        newRow.addView(cell);
                    }
                }
                workoutDataTable.addView(newRow);
            }
        }
    }

    private void populateWaterCalTable(String waterCalData) {
        if (!waterCalData.isEmpty()) {
            String[] waterCalEntries = waterCalData.split("\n\n");

            for (String entry : waterCalEntries) {
                String[] entryValues = entry.split("\n");

                TableRow newRow = new TableRow(this);
                for (String value : entryValues) {
                    if (value.split(": ").length > 1) {
                        TextView cell = new TextView(this);
                        cell.setText(value.split(": ")[1]);
                        cell.setTextColor(Color.WHITE);
                        cell.setPadding(8, 8, 8, 8);
                        newRow.addView(cell);
                    }
                }
                waterCalDataTable.addView(newRow);
            }
        }
    }

    private void deleteLastWorkoutEntry() {
        SharedPreferences sharedPref = getSharedPreferences("fitnessData", MODE_PRIVATE);
        String workoutData = sharedPref.getString("workoutData", "");

        if (!workoutData.isEmpty()) {
            String[] workoutEntries = workoutData.split("\n\n");
            StringBuilder newWorkoutData = new StringBuilder();

            for (int i = 0; i < workoutEntries.length - 1; i++) {
                newWorkoutData.append(workoutEntries[i]).append("\n\n");
            }

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("workoutData", newWorkoutData.toString().trim());
            editor.apply();

            workoutDataTable.removeAllViews();
            populateWorkoutTable(newWorkoutData.toString().trim());
        }
    }

    private void deleteLastWaterCalEntry() {
        SharedPreferences sharedPref = getSharedPreferences("fitnessData", MODE_PRIVATE);
        String waterCalData = sharedPref.getString("waterCalData", "");

        if (!waterCalData.isEmpty()) {
            String[] waterCalEntries = waterCalData.split("\n\n");
            StringBuilder newWaterCalData = new StringBuilder();

            for (int i = 0; i < waterCalEntries.length - 1; i++) {
                newWaterCalData.append(waterCalEntries[i]).append("\n\n");
            }

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("waterCalData", newWaterCalData.toString().trim());
            editor.apply();

            waterCalDataTable.removeAllViews();
            populateWaterCalTable(newWaterCalData.toString().trim());
        }
    }
}