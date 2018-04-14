package com.example.amit.remotedesktop;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Applications extends AppCompatActivity {
    ImageButton a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));

        a = findViewById(R.id.imageButton2);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchApp(3);
            }
        });
    }

    public void launchApp(int a){
        switch (a){
            case 1: MainActivity.msg("FIREFOX");
                break;
            case 2: MainActivity.msg("GEDIT");
                break;
            case 3: MainActivity.msg("CTRL_ALT_T");
                break;
        }



    }
}
