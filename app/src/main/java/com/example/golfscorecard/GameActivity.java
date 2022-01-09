package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.golfscorecard.exceptions.SadPlayerException;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    private final int maxSwings = 7;
    private final String courseNamefix = "Golfiplatz";

    TextView textView;
    Intent intent;
    Bundle bundle;


    ArrayList<String> liste;
    String holes;
    String number;
    String courseName;
    Scorecard scorecard;

    int numberOfHoles;
    int numberOfPlayers;

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //textView = findViewById(R.id.view1);

        getValuesFromBundle();
        createScorecard(liste);
        createGridLayout();

        //textView.setText(liste.toString());

        //TextView textView2 = findViewById(R.id.view2);
        //textView2.setText(courseName);

        //TableRow testRow = new TableRow();
    }

    private void getValuesFromBundle(){
        intent = getIntent();
        bundle = intent.getExtras();
        liste = bundle.getStringArrayList("stringliste");

        holes = bundle.getString("EXTRA_HOLE");
        number = bundle.getString("NUMBER_OF_PLAYER");
        numberOfHoles = Integer.parseInt(holes);
        numberOfPlayers = Integer.parseInt(number);
        courseName = bundle.getString("COURSENAME");
    }

    private void createScorecard(ArrayList<String> names){
        scorecard = new Scorecard(courseNamefix,numberOfHoles,maxSwings);

        for (String s:names) {
            try {
                scorecard.addPlayer(new Player(s,numberOfHoles,maxSwings));
            } catch (SadPlayerException e) {
                System.out.println(e.getMessage()+"\noof iwas war wohl null!");
            }
        }

    }

    public void createGridLayout(){
        gridLayout = findViewById(R.id.score_grid);

        gridLayout.removeAllViews();

        //have smth to the left as well
        int column = numberOfPlayers + 1;
        //1 for the explain row in the first
        //1 for the ergebnis row;
        int row = numberOfHoles +2;
        int total = column * row;

        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row);

        for(int i = 0, c = 0, r = 0; i < total; i++, r++){
            if (r == row){
                r = 0;
                c++;
            }
                EditText toAdd = new EditText(this);
                toAdd.setText(Integer.toString(c)+ "_" + Integer.toString(r) +"; ");
                toAdd.setInputType(InputType.TYPE_CLASS_NUMBER);

                String id = Integer.toString(c) + Integer.toString(r);
                toAdd.setId(Integer.parseInt(id));
                //editTextlocal.setLayoutParams(new LayoutParams());
                /*GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED,1);
                GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED,1);

                if(r == 0 && c == 0) {
                    Log.e("", "spec");
                    colSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
                    rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
                }*/
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridLayout.addView(toAdd,gridParam);
            /*} else{
                EditText toAdd = new EditText(this);
                String id = Integer.toString(c) + Integer.toString(r);
                toAdd.setId(Integer.parseInt(id));
                //editTextlocal.setLayoutParams(new LayoutParams());
                GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED,1);
                GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED,1);

                if(r == 0 && c == 0) {
                    Log.e("", "spec");
                    colSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
                    rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
                }
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan,colSpan);
                gridLayout.addView(toAdd,gridParam);
            }
*/
/*
            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED,1);
            GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED,1);

            if(r == 0 && c == 0) {
                Log.e("", "spec");
                colSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
                rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
            }
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan,colSpan);
            gridLayout.addView(toAdd,gridParam);*/

        }
    }
}