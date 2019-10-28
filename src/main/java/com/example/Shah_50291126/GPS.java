package com.example.Shah_50291126;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GPS extends AppCompatActivity implements LocationListener {

    public  static final int RequestPermissionCode  = 1 ;
    Button buttonEnable, buttonGet ;
    TextView textViewLongitude, textViewLatitude ;
    Context context;
    Intent intent1 ;
    Location location;
    LocationManager locationManager ;
    boolean GpsStatus = false ;
    Criteria criteria ;
    String Holder;
    private static String latitudestr, longitudestr, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        EnableRuntimePermission();

        buttonEnable = (Button)findViewById(R.id.button);
        buttonGet = (Button)findViewById(R.id.button2);

        textViewLongitude = (TextView)findViewById(R.id.textView);
        textViewLatitude = (TextView)findViewById(R.id.textView2);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        criteria = new Criteria();

        Holder = locationManager.getBestProvider(criteria, false);

        context = getApplicationContext();

        CheckGpsStatus();

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent1);


            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckGpsStatus();

                if(GpsStatus == true) {
                    if (Holder != null) {
                        if (ActivityCompat.checkSelfPermission(
                                GPS.this,
                                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                &&
                                ActivityCompat.checkSelfPermission(GPS.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                        != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        location = locationManager.getLastKnownLocation(Holder);
                        locationManager.requestLocationUpdates(Holder, 12000, 7, GPS.this);
                    }
                }else {

                    Toast.makeText(GPS.this, "Please Enable GPS First", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    @Override
    public void onLocationChanged(Location location) {

        textViewLongitude.setText("Longitude: " + location.getLongitude());
        textViewLatitude.setText("Latitude: " + location.getLatitude());

        latitudestr = textViewLatitude.getText().toString();
        longitudestr = textViewLongitude.getText().toString();

        details = latitudestr + longitudestr;

    }

    public static String getData(){
        return details;
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void CheckGpsStatus(){

        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(GPS.this,
                Manifest.permission.ACCESS_FINE_LOCATION))
        {

            Toast.makeText(GPS.this,"ACCESS_FINE_LOCATION permission allows us to Access GPS in app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(GPS.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(GPS.this,"Permission Granted, Your application can access GPS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(GPS.this,"Permission Canceled, Your application cannot access GPS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}