package com.example.golfscorecard;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.golfscorecard.exceptions.SadPlayerException;

import java.util.ArrayList;

public class NamesActivity extends AppCompatActivity {

    Scorecard scorecard;
    Intent intent;
    Bundle extras;
    String holes;
    String number;
    int numberOfHoles;
    int numberOfPlayers;
    Button button;
    ArrayList<String> listeSpieler = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        intent = getIntent();
        extras = intent.getExtras();
        holes = extras.getString("EXTRA_HOLE");
        number = extras.getString("NUMBER_OF_PLAYER");
        numberOfHoles = Integer.parseInt(holes);
        numberOfPlayers = Integer.parseInt(number);
        scorecard = new Scorecard(Integer.toString(R.string.courseName),numberOfHoles,R.integer.maxSwings);
        button = findViewById(R.id.add);
        button.setOnClickListener(this::add);
    }

    protected void add(View view){
        EditText name = findViewById(R.id.name);
        String nameString = name.getText().toString();
        listeSpieler.add(nameString);

        activateButton();
    }

    protected void activateButton(){
        if (listeSpieler.size() == numberOfPlayers){
            Button activateButton = findViewById(R.id.start1);
            activateButton.setEnabled(true);
            button.setEnabled(false);
            button.setBackgroundColor(getResources().getColor(R.color.gray));
            activateButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
            activateButton.setOnClickListener(this::gameStart);
        }
    }

    protected void gameStart(View view){
        Intent intent = new Intent(this, GameActivity.class);
        scorecard.setCourseName("Golfplatz Brudda!");
        intent.putExtra("stringliste", listeSpieler);
        startActivity(intent);
    }
}
/*
        CODE GRAVEYARD:
// Get the intent that started the activity
        Intent intent = getIntent();

        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //multiple
        Bundle extrass = intent.getExtras();
        String holes = extrass.getString("EXTRA_HOLE");
        String number = extrass.getString("NUMBER_OF_PLAYER");

        int numberOfHoles = Integer.parseInt(holes);
        int numberOfPlayers = Integer.parseInt(number);
        Scorecard scorecard = new Scorecard(R.string.courseName,numberOfHoles,R.integer.maxSwings);

        for (int i = 0; i < numberOfPlayers; i++) {
            EditText editText = findViewById(R.id.name);
            String name = editText.getText().toString();
            Player player = new Player(name,numberOfHoles,R.integer.maxSwings);
            try {
                scorecard.addPlayer(player);
            } catch (SadPlayerException e) {
                Toast.makeText(this, "Namen Eingeben bitte!", 2);
            }
        }


        TextView numberText = findViewById(R.id.textView4);
        numberText.setText(number);





                SOLO GRAVE:
        Bundle bundle = new Bundle();
        String courseName = Integer.toString(R.string.courseName);
        bundle.putString("COURSE_NAME",courseName);
        intent.putExtras(bundle);
 */