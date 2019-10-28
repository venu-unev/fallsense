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


public class Light extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "Light";

    private SensorManager sensorManager;
    Sensor light;

    TextView xValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        xValue = (TextView) findViewById(R.id.xValue);


        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(light!=null) {
            sensorManager.registerListener(Light.this, light, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");

        }
        else {
            xValue.setText("Light sensor not supported");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0]);

        xValue.setText("Light: " + sensorEvent.values[0]);

    }
}
