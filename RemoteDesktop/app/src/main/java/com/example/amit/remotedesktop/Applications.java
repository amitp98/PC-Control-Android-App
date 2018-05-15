package com.example.amit.remotedesktop;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Applications extends AppCompatActivity {
    ImageButton a, b, c, d;
    TextView m;
    Button l, r;
    int x, y, X, Y, i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));

        a = findViewById(R.id.imageButton1);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchApp(1);
            }
        });
        b = findViewById(R.id.imageButton2);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchApp(2);
            }
        });
        c = findViewById(R.id.imageButton3);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchApp(3);
            }
        });
        d = findViewById(R.id.imageButton4);
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchApp(4);
            }
        });

        l = findViewById(R.id.lefta);
        r = findViewById(R.id.righta);
        m = findViewById(R.id.textViewa);

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
                if(touch.getAction() == MotionEvent.ACTION_DOWN){       // First touch, get the initial x and y positions
                    x = (int) touch.getX();
                    y = (int) touch.getY();
                    i = 0;
                }

                if(touch.getAction() == MotionEvent.ACTION_MOVE){       // Mouse Move, calculate the distance moved and send it to java app.
                    X = (int) touch.getX() - x;
                    Y = (int) touch.getY() - y;
                    x = (int) touch.getX();                             // Update the initial positions
                    y = (int) touch.getY();
                    MainActivity.msg("MOUSE_MOVE");
                    MainActivity.msg(X);
                    MainActivity.msg(Y);
                    i = 1;
                }
                if(touch.getAction() == MotionEvent.ACTION_UP){         // Tap means left click
                    if(i == 0)
                        MainActivity.msg("LEFT");
                }
                return true;
            }
        });


    }

    public void launchApp(int a){
        switch (a){
            case 1: MainActivity.msg("FIREFOX");
                break;
            case 2: MainActivity.msg("TERMINAL");
                break;
            case 3: MainActivity.msg("GEDIT");
                break;
            case 4: MainActivity.msg("FILE");
                break;
        }
    }
}
