package com.example.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.MessageFormat;

/**
 * BinaryDecimalScreen class represents a screen that allows the user to convert between decimal and binary numbers.
 * It handles user input, performs conversions, and adjusts the UI for edge-to-edge display.
 */
public class BinaryDecimalScreen extends AppCompatActivity {
    Button buttonExit;
    Button buttonAccept;
    EditText decimalInput;
    EditText binaryInput;
    SeekBar numberSlider;
    TextView sliderValue;
    RadioGroup radioGroup;
    RadioButton radioDecimalToBinary;
    RadioButton radioBinaryToDecimal;
    private accelerometerDriver accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decimal_binary);

        accel = new accelerometerDriver(this);
        accel.start();

        buttonExit = findViewById(R.id.button_exit2);
        buttonAccept = findViewById(R.id.button_accept);  // Initialize the Accept button
        decimalInput = findViewById(R.id.decimalInput);
        binaryInput = findViewById(R.id.binaryInput);
        numberSlider = findViewById(R.id.numberSlider);
        sliderValue = findViewById(R.id.sliderValue);
        radioGroup = findViewById(R.id.radioGroup); // Initialize the RadioGroup
        radioDecimalToBinary = findViewById(R.id.radioDecimalToBinary); // Initialize Decimal to Binary RadioButton
        radioBinaryToDecimal = findViewById(R.id.radioBinaryToDecimal); // Initialize Binary to Decimal RadioButton

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the slider listener
        numberSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sliderValue.setText(MessageFormat.format("Selected Number: {0}", progress));
                decimalInput.setText(String.valueOf(progress)); // Update EditText with the slider value
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        buttonExit.setOnClickListener(v -> finish());

        // Set up the Accept button listener
        buttonAccept.setOnClickListener(v -> {
            if (radioDecimalToBinary.isChecked()) {
                // Convert from decimal to binary
                String decimalText = decimalInput.getText().toString();
                if (!decimalText.isEmpty()) {
                    try {
                        int decimalNumber = Integer.parseInt(decimalText);
                        String binaryResult = DecimalDriver.decimalToBinary(decimalNumber);
                        binaryInput.setText(binaryResult);
                        Toast.makeText(BinaryDecimalScreen.this, "Converted Decimal to Binary", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(BinaryDecimalScreen.this, "Please enter a valid decimal number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BinaryDecimalScreen.this, "Please enter a value in the decimal field", Toast.LENGTH_SHORT).show();
                }
            } else if (radioBinaryToDecimal.isChecked()) {
                // Convert from binary to decimal
                String binaryText = binaryInput.getText().toString();
                if (!binaryText.isEmpty()) {
                    try {
                        int decimalResult = DecimalDriver.binaryToDecimal(binaryText);
                        decimalInput.setText(String.valueOf(decimalResult));
                        Toast.makeText(BinaryDecimalScreen.this, "Converted Binary to Decimal", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(BinaryDecimalScreen.this, "Please enter a valid binary number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BinaryDecimalScreen.this, "Please enter a value in the binary field", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(BinaryDecimalScreen.this, "Please select a conversion type", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accel.stop();
    }
}