package com.example.lab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * SecondaryActivity class represents a screen that allows the user to convert between decimal and binary numbers.
 * It handles user input, performs conversions, and adjusts the UI for edge-to-edge display.
 */
public class SecondaryActivity extends AppCompatActivity
{
    /** Button to exit the activity. */
    Button buttonExit;

    /** Button to convert decimal to binary. */
    Button buttonDecimalToBinary;

    /** Button to convert binary to decimal. */
    Button buttonBinaryToDecimal;

    /** EditText field to input the decimal number. */
    EditText decimalInput;

    /** EditText field to input the binary number. */
    EditText binaryInput;

    /**
     * Called when the activity is starting. This method sets up the UI components,
     * handles system window insets, and assigns click listeners for the conversion buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down, this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b><i>Note: Otherwise, it is null.</i></b>
     */
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_secondary);

        // Initialize UI components
        buttonExit = findViewById(R.id.button_exit2);
        buttonDecimalToBinary = findViewById(R.id.calculateButton_DecimalToBinary);
        buttonBinaryToDecimal = findViewById(R.id.calculateButton_BinaryToDecimal);
        decimalInput = findViewById(R.id.decimalInput);
        binaryInput = findViewById(R.id.binaryInput);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set click listener for exit button to close the activity
        buttonExit.setOnClickListener(v -> finish());

        // Set click listener to convert decimal to binary
        buttonDecimalToBinary.setOnClickListener(v ->
        {
            try {
                String decimalText = decimalInput.getText().toString();
                int decimalNumber = Integer.parseInt(decimalText);
                String binaryResult = SDB_Driver.decimalToBinary(decimalNumber);
                binaryInput.setText(binaryResult);
            }
            catch (NumberFormatException e)
            {
                Toast.makeText
                (
                    SecondaryActivity.this,
                    "Please enter a valid decimal number",
                    Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Set click listener to convert binary to decimal
        buttonBinaryToDecimal.setOnClickListener(v -> {
            try {
                String binaryText = binaryInput.getText().toString();
                int decimalResult = SDB_Driver.binaryToDecimal(binaryText);
                decimalInput.setText(String.valueOf(decimalResult));
            } catch (NumberFormatException e) {
                Toast.makeText
                (
                    SecondaryActivity.this,
                    "Please enter a valid binary number",
                    Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
