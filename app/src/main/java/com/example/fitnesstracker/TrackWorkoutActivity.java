package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TrackWorkoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ft_trackworkout);

        Intent mainintent = getIntent();

        TextView WorkoutTitle = findViewById(R.id.WorkoutTitleText);
        TextView SelectWorkout = findViewById(R.id.SelectWorkout);
        TextView EnterReps = findViewById(R.id.EnterRepsText);
        EditText EditReps = findViewById(R.id.EditRepsText);
        TextView EnterSets = findViewById(R.id.EnterSetsText);
        EditText EditSets = findViewById(R.id.EditSetsText);

        Spinner spinner = findViewById(R.id.dropdownmenu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final EditText spinnerTextBox = findViewById(R.id.EditWorkoutSelect);

        Button submitButton = findViewById(R.id.WorkoutSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workoutType = spinnerTextBox.getText().toString();
                String repsString = EditReps.getText().toString();
                String setsString = EditSets.getText().toString();

                if (workoutType.isEmpty() || repsString.isEmpty() || setsString.isEmpty()) {
                    Toast.makeText(TrackWorkoutActivity.this, "Please fill in all data entries", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPref = getSharedPreferences("fitnessData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    int reps = Integer.parseInt(repsString);
                    int sets = Integer.parseInt(setsString);

                    String workoutData = "Workout: " + workoutType + "\nReps: " + reps + "\nSets: " + sets + "\n\n";

                    String existingWorkoutData = sharedPref.getString("workoutData", "");
                    editor.putString("workoutData", existingWorkoutData + workoutData);
                    editor.apply();
                    Toast.makeText(TrackWorkoutActivity.this, "Workout data saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        EditText spinnerTextBox = findViewById(R.id.EditWorkoutSelect);
        if (position == 0) {
            spinnerTextBox.setText(""); // Set blank text
        } else {
            spinnerTextBox.setText(selectedItem);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        EditText spinnerTextBox = findViewById(R.id.EditWorkoutSelect);
        spinnerTextBox.setText(""); // Set blank text
    }
}