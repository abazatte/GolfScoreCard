package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = findViewById(R.id.textView3);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<String> liste = bundle.getStringArrayList("stringliste");
        textView.setText(liste.toString());
    }
}