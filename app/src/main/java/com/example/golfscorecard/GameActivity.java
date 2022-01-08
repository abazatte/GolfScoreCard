package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = findViewById(R.id.textView3);
        TextView anzahl = findViewById(R.id.textView5);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        ArrayList spielerlist = b.getIntegerArrayList("arraylist");

        int anzahl1 = b.getInt("anzahl");
        int anzahl2 = b.getInt("spieler");
        Toast.makeText(this, spielerlist.toString() + " \nAnzahl der Holes: " + anzahl1 + "\n Anzahl Spieler: " + anzahl2, Toast.LENGTH_LONG).show();
        //String anzahl1 = b.getString("tracks");
        //String name = b.getString("platzname");
        //anzahl.setText(anzahl1);
        //textView.setText(name);

    }
}