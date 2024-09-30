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

public class BinaryStringScreen extends AppCompatActivity
{
    Button buttonExit;
    Button buttonStringToBinary;
    Button buttonBinaryToString;
    EditText stringInput;
    EditText binaryInput;

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_binary_string);

        buttonExit = findViewById(R.id.button_exit3);
        buttonStringToBinary = findViewById(R.id.calculateButton_StringToBinary);
        buttonBinaryToString = findViewById(R.id.calculateButton_BinaryToString);
        stringInput = findViewById(R.id.stringInput);
        binaryInput = findViewById(R.id.binaryInput2);

        // Adjust padding for insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonExit.setOnClickListener(v -> finish());

        buttonStringToBinary.setOnClickListener(v ->
        {
            String inputString = stringInput.getText().toString();

            if (inputString.isEmpty()) {stringInput.setError("Input cannot be empty");}
            else
            {
                try
                {
                    String binaryResult = StringDriver.stringToBinary(inputString);
                    binaryInput.setText(binaryResult);
                }
                catch (Exception e)
                {
                    Toast.makeText
                    (
                        BinaryStringScreen.this,
                        "Error during conversion. Please enter a valid string.",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        // Binary to String conversion
        buttonBinaryToString.setOnClickListener(v ->
        {
            String inputBinary = binaryInput.getText().toString();

            if (inputBinary.isEmpty()) {binaryInput.setError("Input cannot be empty");}
            else if (!inputBinary.matches("[01 ]+")) {binaryInput.setError("Invalid binary number! Enter only 0s and 1s.");}
            else
            {
                try
                {
                    String stringResult = StringDriver.binaryToString(inputBinary);
                    stringInput.setText(stringResult);
                }
                catch (Exception e)
                {
                    Toast.makeText
                    (
                        BinaryStringScreen.this,
                        "Error during conversion. Please enter a valid binary number.",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}