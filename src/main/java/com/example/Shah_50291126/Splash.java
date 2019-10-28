package com.example.Shah_50291126;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Splash extends Activity {

    CountDownTimer StartJapa_Timer;
    TextView count;
    long millisUntilFinished;
    long counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        StartJapa_Timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();


            }
            @Override
            public void onTick(long millisUntilFinished) {



            }
        };
        StartJapa_Timer.start();
    }
}