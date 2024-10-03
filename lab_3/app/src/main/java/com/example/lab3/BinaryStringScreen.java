package com.example.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BinaryStringScreen extends AppCompatActivity {
    private accelerometerDriver accelerometer;
    private RadioButton radioButtonStringToBinary;
    private RadioButton radioButtonBinaryToString;
    private EditText stringInput;
    private EditText binaryInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_binary_string);

        // Initialize accelerometer
        accelerometer = new accelerometerDriver(this);
        accelerometer.start();

        // Find views by their IDs
        Button buttonExit = findViewById(R.id.button_exit3);
        Button buttonAccept = findViewById(R.id.button_accept); // Accept button for conversions
        radioButtonStringToBinary = findViewById(R.id.radioButton_StringToBinary);
        radioButtonBinaryToString = findViewById(R.id.radioButton_BinaryToString);
        stringInput = findViewById(R.id.stringInput);
        binaryInput = findViewById(R.id.binaryInput2);

        // Adjust padding for insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Exit button functionality
        buttonExit.setOnClickListener(v -> finish());

        // Accept button functionality based on selected RadioButton
        buttonAccept.setOnClickListener(v -> {
            if (radioButtonStringToBinary.isChecked()) {
                convertStringToBinary();
            } else if (radioButtonBinaryToString.isChecked()) {
                convertBinaryToString();
            } else {
                Toast.makeText(BinaryStringScreen.this, "Please select a conversion option.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void convertStringToBinary() {
        String inputString = stringInput.getText().toString().trim(); // Trim whitespace

        if (inputString.isEmpty()) {
            stringInput.setError("Input cannot be empty");
        } else {
            try {
                String binaryResult = StringDriver.stringToBinary(inputString);
                binaryInput.setText(binaryResult);
            } catch (Exception e) {
                Toast.makeText(
                        BinaryStringScreen.this,
                        "Error during conversion. Please enter a valid string.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void convertBinaryToString() {
        String inputBinary = binaryInput.getText().toString().trim(); // Trim whitespace

        if (inputBinary.isEmpty()) {
            binaryInput.setError("Input cannot be empty");
        } else if (!inputBinary.matches("[01 ]+")) {
            binaryInput.setError("Invalid binary number! Enter only 0s and 1s.");
        } else {
            try {
                String stringResult = StringDriver.binaryToString(inputBinary);
                stringInput.setText(stringResult);
            } catch (Exception e) {
                Toast.makeText(
                        BinaryStringScreen.this,
                        "Error during conversion. Please enter a valid binary number.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accelerometer.stop();
    }
}
