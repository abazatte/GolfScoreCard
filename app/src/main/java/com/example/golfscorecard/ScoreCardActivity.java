package com.example.golfscorecard;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        // Get the intent that started the activity
        Intent intent = getIntent();

        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //multiple
        Bundle extrass = intent.getExtras();
        String name = extrass.getString("EXTRA_HOLE");
        String number = extrass.getString("EXTRA_PLAYER");


        // capture/get the layouts textview and use it
        TextView textView = findViewById(R.id.textView3);
        textView.setText(name);

        TextView numberText = findViewById(R.id.textView4);
        numberText.setText(number);
    }
}