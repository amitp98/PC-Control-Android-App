package com.example.amit.remotedesktop;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Keyboard extends AppCompatActivity {

    Button a, b, d, e, f, g, h ,j, k, l, home,  lt, rt;
    EditText m;
    TextView tp;
    int x, y, X, Y, i;
    private String previousText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        a = findViewById(R.id.button2);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(27);
                /* Type Code of Esc is 27 */
            }
        });

        b = findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(17);
            }
        });
        home = findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(36);
            }
        });

        d = findViewById(R.id.button6);
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(16);
            }
        });

        e = findViewById(R.id.button7);
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(9);
            }
        });

        f = findViewById(R.id.button8);
        f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(10);
            }
        });

        g = findViewById(R.id.button9);
        g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(8);
            }
        });

        h = findViewById(R.id.button10);
        h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("TYPE_KEY");
                MainActivity.msg(127);
            }
        });

        j = findViewById(R.id.button12);
        j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("CTRL_ALT_T");
            }
        });

        k = findViewById(R.id.button13);
        k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("CTRL_SHIFT_Z");
            }
        });

        l = findViewById(R.id.button14);
        l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.msg("ALT_F4");
            }
        });


        lt = findViewById(R.id.leftk);
        rt = findViewById(R.id.rightk);
        tp = findViewById(R.id.textViewk);

        lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.msg("LEFT");
            }
        });
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.msg("RIGHT");
            }
        });

        tp.setOnTouchListener(new View.OnTouchListener() {

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


        m = findViewById(R.id.editText);
        m.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                char ch = newCharacter(s, previousText);
                if (ch == 0) {
                    return;
                }
                MainActivity.msg("TYPE_CHARACTER");
                MainActivity.msg(Character.toString(ch));
                previousText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private char newCharacter(CharSequence currentText, CharSequence previousText) {
        char ch = 0;
        int currentTextLength = currentText.length();
        int previousTextLength = previousText.length();
        int difference = currentTextLength - previousTextLength;
        if (currentTextLength > previousTextLength) {
            if (1 == difference) {
                ch = currentText.charAt(previousTextLength);
            }
        } else if (currentTextLength < previousTextLength) {
            if (-1 == difference) {
                ch = '\b';
            } else {
                ch = ' ';
            }
        }
        return ch;
    }
}
