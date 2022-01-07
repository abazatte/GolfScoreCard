package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::sendMessage);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, NamesActivity.class);
        EditText hole = findViewById(R.id.hole);
        EditText player = findViewById(R.id.player);

        String holeString = hole.getText().toString();
        String playerString = player.getText().toString();

        Bundle extra = new Bundle();
        extra.putString("EXTRA_HOLE", holeString);
        extra.putString("NUMBER_OF_PLAYER", playerString);
        intent.putExtras(extra);
        startActivity(intent);
    }
}