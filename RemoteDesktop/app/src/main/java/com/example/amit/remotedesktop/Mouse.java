package com.example.amit.remotedesktop;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Mouse extends AppCompatActivity {
    Button l, r;
    TextView m;
    int a, b, A, B, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse2);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        l = findViewById(R.id.left);
        r = findViewById(R.id.right);
        m = findViewById(R.id.textView4);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.msg("LEFT");
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.msg("RIGHT");
            }
        });

        m.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent touch) {
                if(touch.getAction() == MotionEvent.ACTION_DOWN){		// First touch, get the initial x and y positions
                    a = (int) touch.getX();
                    b = (int) touch.getY();
                    i = 0;
                }

                if(touch.getAction() == MotionEvent.ACTION_MOVE){ 		// Mouse Move, calculate the distance moved and send it to java app.	
                    A = (int) touch.getX() - a;
                    B = (int) touch.getY() - b;
                    a = (int) touch.getX();								// Update the initial positions
                    b = (int) touch.getY();
                    MainActivity.msg("MOUSE_MOVE");
                    MainActivity.msg(A);
                    MainActivity.msg(B);
                    i = 1;
                }
                if(touch.getAction() == MotionEvent.ACTION_UP){			// Tap means left click
                    if(i == 0)
                        MainActivity.msg("LEFT");
                }
                return true;
            }
        });
    }
}