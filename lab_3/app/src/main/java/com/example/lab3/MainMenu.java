package com.example.lab3;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
 * TODO:
 *  - implement the credits page to feature your sources and school information
 *  - implement an accelerometer to detect whether the user is shaking their phone
 */

/**
 * MainMenu class represents the main screen of the application. It handles UI initialization,
 * button click listeners, and window insets adjustments for edge-to-edge display.
 */
public class MainMenu extends AppCompatActivity
{
    Button buttonExit;
    Button buttonBinaryDecimal;
    Button buttonBinaryString;
    Button buttonCredits;

    /**
     * Called when the activity is starting. This method is responsible for setting up the UI components,
     * handling system window insets, and assigning click listeners to buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down, this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b><i>Note: Otherwise, it is null.</i></b>
     */
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        buttonBinaryDecimal = findViewById(R.id.buttonBinaryDecimal);
        buttonBinaryString = findViewById(R.id.buttonBinaryString);
        buttonCredits = findViewById(R.id.buttonCredits);
        buttonExit = findViewById(R.id.buttonExit);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonBinaryDecimal.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, BinaryDecimalScreen.class)));
        buttonBinaryString.setOnClickListener(v  -> startActivity(new Intent(MainMenu.this, BinaryStringScreen.class)));
        buttonCredits.setOnClickListener(v       -> startActivity(new Intent(MainMenu.this, CreditScreen.class)));
        buttonExit.setOnClickListener(v -> finish());
    }
}