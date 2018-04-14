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


        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView1);
        textView.setText("Android app to control PC!\n" +
                "\n" +
                "1. Touchpad\n" +
                "2. Keyboard\n" +
                "3. Presentation");
    }
}
