package com.example.Shah_50291126;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }


    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        Intent intent = new Intent(MainActivity.this, Accelerometer.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI==1) {
                        Intent intent = new Intent(MainActivity.this, Gyroscope.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI==2) {
                        Intent intent = new Intent(MainActivity.this, Magnetometer.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI==3) {
                        Intent intent = new Intent(MainActivity.this, Light.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI==4) {
                        Intent intent = new Intent(MainActivity.this, Proximity.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);

                    }
                    else if (finalI==5){
                        Intent intent = new Intent(MainActivity.this, GPS.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);
                    }
                    else if (finalI==6){
                        Intent intent = new Intent(MainActivity.this, Fall.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, About.class);
                        intent.putExtra("info", "This is activity from card item index  " + finalI);
                        startActivity(intent);
                    }
                }
            });
        }
    }


}
