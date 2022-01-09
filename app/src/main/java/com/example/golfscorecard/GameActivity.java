package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.golfscorecard.exceptions.HoleDoesntExistException;
import com.example.golfscorecard.exceptions.InvalidScoreException;
import com.example.golfscorecard.exceptions.SadPlayerException;
import com.google.android.material.chip.ChipGroup;

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
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        gridLayout = findViewById(R.id.score_grid);

        gridLayout.removeAllViews();

        //have smth to the left as well
        int numberOfColumns = numberOfPlayers + 1;
        int widthOfCell = (int)(screenWidth /numberOfColumns);
        //1 for the explain row in the first
        //1 for the ergebnis row;

        int numberOfRows = numberOfHoles +2;
        int heightOfCell = (int) (170);
        int total = numberOfColumns * numberOfRows;

        gridLayout.setColumnCount(numberOfColumns);
        gridLayout.setRowCount(numberOfRows);

        for(int i = 0, c = 0, r = 0; i < total; i++, r++){
            if (r == numberOfRows){
                r = 0;
                c++;
            }
            if(r == 0 && c == 0){
                TextView txtView = new TextView(this);
                String id = Integer.toString(c) + Integer.toString(r);
                txtView.setId(Integer.parseInt(id));
                txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                txtView.setLayoutParams(parms);
                txtView.setGravity(Gravity.CENTER);

                txtView.setBackgroundResource(R.drawable.cell_shape);

                //txtView.setText("_");
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.CENTER);
                gridLayout.addView(txtView,gridParam);

            } else if(r == 0){
                TextView txtView = new TextView(this);
                String id = Integer.toString(c) + Integer.toString(r);
                txtView.setId(Integer.parseInt(id));
                txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                txtView.setLayoutParams(parms);
                txtView.setGravity(Gravity.CENTER);
                txtView.setBackgroundResource(R.drawable.cell_shape);
                txtView.setTextSize(20);
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.CENTER);
                txtView.setText(liste.get(c-1));



                gridLayout.addView(txtView,gridParam);

            } else if(c == 0 && r == numberOfRows-1){
                TextView txtView = new TextView(this);
                String id = Integer.toString(c) + Integer.toString(r);
                txtView.setId(Integer.parseInt(id));
                txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                txtView.setLayoutParams(parms);
                txtView.setGravity(Gravity.CENTER);
                txtView.setBackgroundResource(R.drawable.cell_shape);
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.CENTER);
                txtView.setText("Summe");
                txtView.setTextSize(20);

                gridLayout.addView(txtView,gridParam);
            }else if(r == numberOfRows-1){
                TextView txtView = new TextView(this);
                String id = Integer.toString(c) + Integer.toString(r);
                txtView.setId(Integer.parseInt(id));
                txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                txtView.setLayoutParams(parms);
                txtView.setGravity(Gravity.CENTER);

                txtView.setBackgroundResource(R.drawable.cell_shape);
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.CENTER);
                //txtView.setText("Summe");

                gridLayout.addView(txtView,gridParam);
            }
            else if(c == 0){
                TextView txtView = new TextView(this);
                String id = Integer.toString(c) + Integer.toString(r);
                txtView.setId(Integer.parseInt(id));
                txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ViewGroup.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                txtView.setLayoutParams(parms);
                txtView.setGravity(Gravity.CENTER);
                txtView.setBackgroundResource(R.drawable.cell_shape);
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.CENTER);
                txtView.setText(Integer.toString(r));
                txtView.setTextSize(20);
                gridLayout.addView(txtView,gridParam);
            }else{
                EditText toAdd = new EditText(this);
                //toAdd.setText(Integer.toString(c)+ "_" + Integer.toString(r) +"; ");
                toAdd.setInputType(InputType.TYPE_CLASS_NUMBER);
                toAdd.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                toAdd.setBackgroundResource(R.drawable.cell_shape);
                String id = "1000" + Integer.toString(c) + Integer.toString(r);
                toAdd.setId(Integer.parseInt(id));
                GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams();
                gridParam.width = widthOfCell;
                gridParam.height = heightOfCell;
                gridParam.setGravity(Gravity.FILL);

                toAdd.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        updateScores();
                    }
                });


                gridLayout.addView(toAdd,gridParam);


            }

        }
    }

    public void updateScores(){
        ArrayList<Player> playerlist = scorecard.getPlayerList();
        for (int i = 0; i < playerlist.size(); i++) {
            Player p = playerlist.get(i);
            for (int j = 1; j < numberOfHoles; j++) {
                String id = "1000" + Integer.toString((i+1)) + Integer.toString(j);
                int realID = Integer.parseInt(id);
                int resourceId = getResources().getIdentifier(
                        id,
                        "id",
                        this.getPackageName());
                EditText edt = findViewById(resourceId);

                try{
                    p.updateHole(j,Integer.parseInt(edt.getText().toString()));
                } catch (HoleDoesntExistException | InvalidScoreException | NumberFormatException h){
                    continue;
                }
            }
            String id = Integer.toString((i+1)) + Integer.toString(numberOfHoles + 1);
            int realID = Integer.parseInt(id);
            int resourceId = getResources().getIdentifier(
                    id,
                    "id",
                    this.getPackageName());
            TextView txt = findViewById(resourceId);
            txt.setText(p.getSum());
        }
    }
}