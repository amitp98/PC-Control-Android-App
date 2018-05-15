package com.example.amit.remotedesktop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView1);
        textView.setText("Android app to control PC\n\n" +
                "\n" +
                "> Touchpad (Mouse, Left and \n\t Right Click)\n\n" +
                "> Keyboard (Input Text and few\n\t commonly used Keys) \n\n" +
                "> Presentation (Slideshow, Pointer \n\t as Pen, Volume Buttons for \n\t Slide change) \n\n" +
                "> Launch Applications (Firefox, \n\t Terminal, Gedit, File Manager)\n\n"
                );
    }
}
