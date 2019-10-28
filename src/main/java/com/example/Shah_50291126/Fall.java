package com.example.Shah_50291126;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fall extends AppCompatActivity{

    Button btnSendSMS, current;
    TextView number;
    EditText txtPhoneNo;
    private static String phoneNo;

    private static final int SEND_SMS_CODE = 100;


    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Fall.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Fall.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(Fall.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == SEND_SMS_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Fall.this,
                        "Test Service Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(Fall.this,
                        "Test Service Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);



        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        current = (Button) findViewById(R.id.current);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        number = (TextView) findViewById(R.id.number);

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(
                        Manifest.permission.SEND_SMS,
                        SEND_SMS_CODE);
                phoneNo = txtPhoneNo.getText().toString();
                Toast.makeText(getApplicationContext(), "Number Updated!", Toast.LENGTH_SHORT).show();
            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(phoneNo);
            }
        });





    }

    public static String getData(){
        return phoneNo;
    }

}
