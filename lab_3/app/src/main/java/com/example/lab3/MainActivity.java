package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity class represents the main screen of the application. It handles UI initialization,
 * button click listeners, and window insets adjustments for edge-to-edge display.
 */
public class MainActivity extends AppCompatActivity
{
    /** Button to exit the application. */
    Button buttonExit;

    /** Button to navigate to the secondary activity. */
    Button buttonStart;

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

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        buttonExit = findViewById(R.id.button_exit);
        buttonStart = findViewById(R.id.button_start);

        // Apply window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set a click listener for the exit button to finish the activity
        buttonExit.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v) {finish();}
        });

        // Set a click listener for the start button to navigate to the SecondaryActivity
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SecondaryActivity.class));
            }
        });
    }
}