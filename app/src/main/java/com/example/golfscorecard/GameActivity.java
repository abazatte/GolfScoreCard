package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = findViewById(R.id.textView3);
        TextView anzahl = findViewById(R.id.textView4);

        Scorecard scorecard = getIntent().getParcelableExtra("parcelboi");
        String anzahl1 = Integer.toString(scorecard.getAnzahl());
        textView.setText(scorecard.courseName);
        anzahl.setText(anzahl1);
    }
}