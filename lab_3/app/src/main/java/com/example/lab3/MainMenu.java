package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {

    Button buttonExit;
    Button buttonCredits;
    Spinner spinnerConverterOptions;

    private accelerometerDriver accel;
    private boolean isSpinnerInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        accel = new accelerometerDriver(this);
        accel.start();

        buttonCredits = findViewById(R.id.buttonCredits);
        buttonExit = findViewById(R.id.buttonExit);
        spinnerConverterOptions = findViewById(R.id.spinnerConverterOptions);

        // Set up the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.converter_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConverterOptions.setAdapter(adapter);

        // Handle spinner item selection
        spinnerConverterOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if (isSpinnerInitialized) {
                    switch (position) {
                        case 1:
                            startActivity(new Intent(MainMenu.this, BinaryStringScreen.class));
                            break;
                        case 2:
                            startActivity(new Intent(MainMenu.this, BinaryDecimalScreen.class));
                            break;
                    }
                } else {
                    isSpinnerInitialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonCredits.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, CreditScreen.class)));
        buttonExit.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accel.stop();
    }
}
