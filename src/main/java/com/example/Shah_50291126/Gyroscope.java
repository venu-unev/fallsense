package com.example.Shah_50291126;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class Gyroscope extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "Gyroscope";

    private SensorManager sensorManager;
    Sensor gyroscope;

    TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope!=null) {
            sensorManager.registerListener(Gyroscope.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");

        }
        else {
            xValue.setText("Gyroscope not supported");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);

        xValue.setText("X: " + sensorEvent.values[0]);
        yValue.setText("Y: " + sensorEvent.values[1]);
        zValue.setText("Z: " + sensorEvent.values[2]);
    }
}
