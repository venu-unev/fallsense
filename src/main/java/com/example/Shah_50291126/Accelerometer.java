package com.example.Shah_50291126;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import android.telephony.SmsManager;


import androidx.appcompat.app.AppCompatActivity;


public class Accelerometer extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "Accelerometer";

    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView xValue, yValue, zValue;


    int x, y, z;
    private int index;
    private float[] accZ, accX, accY;
    private double rootSquare;

    String phoneNo = Fall.getData();
    String details = GPS.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer!=null) {
            sensorManager.registerListener(Accelerometer.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");

        }
        else {
            xValue.setText("Accelerometer not supported");
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



        if(sensorEvent.values[2]>13){

            Toast.makeText(this,"You fell!! Sending help text to " + phoneNo, Toast.LENGTH_SHORT).show();
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phoneNo, null, "Help me! I just fell down. My co-ordinates are " + details, null, null);

        }
    }
}
