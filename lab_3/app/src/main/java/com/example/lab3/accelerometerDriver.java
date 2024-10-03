package com.example.lab3;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 *
 */
public class accelerometerDriver {
    private final SensorManager sensorManager;
    private final SensorEventListener listener;
    private final Context context;

    /**
     * Constructor to initialize the driver
     *
     * @param context
     */
    public accelerometerDriver(Context context) {
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float xValue = Math.abs(event.values[0]);
                float yValue = Math.abs(event.values[1]);
                float zValue = Math.abs(event.values[2]);
                if (xValue > 15 || yValue > 15 || zValue > 15) {
                    showShakeDialog();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    /**
     * Method to start listening to accelerometer events
     */
    public void start() {
        if (sensorManager != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor != null) {
                sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                showAlert();
            }
        }
    }

    /**
     * Method to stop listening to accelerometer events
     */
    public void stop() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    /**
     * General method to show an alert dialog
     */
    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Accelerometer not available")
                .setMessage("This device does not have an accelerometer sensor.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    /**
     * Method to show the popup dialog for shake detection
     */
    private void showShakeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Shake Detected")
                .setMessage("The device was shaken!")
                .setPositiveButton("Thank You!", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}