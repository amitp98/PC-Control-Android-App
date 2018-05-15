package com.example.amit.remotedesktop;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ppt extends AppCompatActivity {
    Button a1, b1, c, d, e;
    Button l, r, ptr;
    TextView m;
    int a, b, A, B, i, press, ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellow)));

        press = ss = 0;													// press and slideshow flags initialised to 0
        a1 = findViewById(R.id.button19);
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ss == 0){
                    MainActivity.msg("F5");								// Slideshow
                    ss = 1;
                }
                else if(ss == 1){
                    MainActivity.msg("TYPE_KEY");						// Exit Slideshow(Esc key)
                    MainActivity.msg(27);
                    ss = 0;
                }
            }
        });

        b1 = findViewById(R.id.button20);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("RIGHT_ARROW_KEY");
            }
        });
        c = findViewById(R.id.button21);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("LEFT_ARROW_KEY");
            }
        });
        d = findViewById(R.id.button22);
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("ShiftF5");							// Slideshow from current slide (Shift+F5)		
                ss = 1;
            }
        });
        e = findViewById(R.id.button23);
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("ALT_F4");
            }
        });

        ptr = findViewById(R.id.leftp);
        m = findViewById(R.id.textViewp);

        l = findViewById(R.id.leftc);
        r = findViewById(R.id.rightc);

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
        ptr.setOnClickListener(new View.OnClickListener() {					// Use pointer as pen

            @Override
            public void onClick(View pView) {
                if(press == 1){
                    MainActivity.msg("LEFT_R");								// Press and hold left key
                    press = 0;
                }
                else if(press == 0){
                    MainActivity.msg("LEFT_P");								// Release left key
                    press = 1;
                }

            }
        });

        m.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent touch) {
                if(touch.getAction() == MotionEvent.ACTION_DOWN){
                    a = (int) touch.getX();
                    b = (int) touch.getY();
                    i = 0;
                }

                if(touch.getAction() == MotionEvent.ACTION_MOVE){
                    A = (int) touch.getX() - a;
                    B = (int) touch.getY() - b;
                    a = (int) touch.getX();
                    b = (int) touch.getY();
                    MainActivity.msg("MOUSE_MOVE");
                    MainActivity.msg(A);
                    MainActivity.msg(B);
                    i = 1;
                }
                if(touch.getAction() == MotionEvent.ACTION_UP){
                    if(i == 0)
                        MainActivity.msg("LEFT");
                }

                return true;
            }

        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    MainActivity.msg("RIGHT_ARROW_KEY");
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    MainActivity.msg("LEFT_ARROW_KEY");
                    return true;
                }
                return true;
        }
        return true;

    }

}

