package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.golfscorecard.exceptions.SadPlayerException;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    private final int maxSwings = 7;
    private final String courseName = "Golfiplatz";

    TextView textView;
    Intent intent;
    Bundle bundle;


    ArrayList<String> liste;
    String holes;
    String number;
    Scorecard scorecard;

    int numberOfHoles;
    int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView = findViewById(R.id.textView3);

        getValuesFromBundle();
        createScorecard(liste);

        textView.setText(liste.toString());
    }

    private void getValuesFromBundle(){
        intent = getIntent();
        bundle = intent.getExtras();
        liste = bundle.getStringArrayList("stringliste");

        holes = bundle.getString("EXTRA_HOLE");
        number = bundle.getString("NUMBER_OF_PLAYER");
        numberOfHoles = Integer.parseInt(holes);
        numberOfPlayers = Integer.parseInt(number);
    }

    private void createScorecard(ArrayList<String> names){
        scorecard = new Scorecard(courseName,numberOfHoles,maxSwings);

        for (String s:names) {
            try {
                scorecard.addPlayer(new Player(s,numberOfHoles,maxSwings));
            } catch (SadPlayerException e) {
                System.out.println(e.getMessage()+"\noof iwas war wohl null!");
            }
        }

    }

    /*
    private ArrayList<Player> createPlayers(ArrayList<String> names){
        ArrayList<Player> players = new ArrayList<>();
        for(String s: names){
            players.add()
        }

        return;
    }*/
}