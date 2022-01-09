package com.example.golfscorecard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class SpreadSheet extends AppCompatActivity {
    public static int anzahlbahnen=16;
    public static int anzahlSpieler=5;

    ArrayList<String> spielerNamen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spreadsheet);

        Intent intent=this.getIntent();
        anzahlbahnen=intent.getIntExtra("NumberOfHoles",10);
        anzahlSpieler=intent.getIntExtra("AnzahlSpieler",1);
        this.spielerNamen=intent.getStringArrayListExtra("PlayerNames");


        GridLayout grid = this.findViewById(R.id.grid);
        grid.removeAllViews();
        grid.setRowCount(anzahlbahnen);
        grid.setColumnCount(anzahlSpieler + 1);


        Button newGameButton = this.findViewById(R.id.newGameButton);
        Button endGameButton = this.findViewById(R.id.endGameButton);

        newGameButton.setOnClickListener(this::startNewGameMenu);
        endGameButton.setOnClickListener(this::endGame);

        this.addIntensionColumn(grid, "Hole", 140,true);
        for(int i=0;i<anzahlSpieler;i++)
            this.addIntensionColumn(grid, this.spielerNamen.get(i), (1080 - 140) / anzahlSpieler,false);



        for(int bahnnummer=1;bahnnummer<anzahlbahnen+1;bahnnummer++) {
            createRow(grid, bahnnummer, this);
            for(int spielernummer=0;spielernummer<anzahlSpieler;spielernummer++) {
                createFields(grid,bahnnummer,spielernummer,this);//EDIT
            }
        }

        this.fillResults();
        this.setResults();
    }

    private void endGame(View view) {
        int lowestScore = Integer.MAX_VALUE;
        int personOfLowestScore = 0;
        for(int i = 0; i< spielerNamen.size();i++){
            TextView txtview = findViewById(i+10001);
            int bla = Integer.parseInt(txtview.getText().toString());

            if (bla<lowestScore){
                personOfLowestScore = i;
                lowestScore = bla;
            }
        }
        String name = spielerNamen.get(personOfLowestScore);

        Toast.makeText(this,"Gewinner: " + name + " mit " + lowestScore + " Punkten!",Toast.LENGTH_LONG).show();


    }

    private void startNewGameMenu(View view) {

        Intent intent = new Intent(SpreadSheet.this,MainActivity.class);
        SpreadSheet.this.startActivity(intent);


    }

    void setResults(){
        for(int pNumber=0;pNumber<anzahlSpieler;pNumber++) {
            int gesamt=0;
            for (int bahnnummer = 1; bahnnummer < anzahlbahnen + 1; bahnnummer++) {
                EditText x = SpreadSheet.this.findViewById(bahnnummer + pNumber * 100);
                try {
                    int y=(Integer.parseInt(String.valueOf(x.getText())));
                    gesamt+=y;
                }catch (NumberFormatException e){;}
            }
            TextView tv = this.findViewById(10000+pNumber+1);
            tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.cool_purple, this.getTheme()));
            tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.cool_purple, this.getTheme()));
            tv.setText(Integer.toString(gesamt));
        }
    }

    void fillResults(){
        GridLayout grid = this.findViewById(R.id.results);
        grid.removeAllViews();
        grid.setRowCount(anzahlbahnen);
        grid.setColumnCount(anzahlSpieler + 1);
        this.createResultTable(grid, "", 144,99999999);
        for(int i=1;i<anzahlSpieler+1;i++){
            this.createResultTable(grid, "", (1080 - 140) / anzahlSpieler,10000+i);
        }

    }

    private void createResultTable(GridLayout grid, String text, int width, int id){
        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setPadding(0,20,0,0);
        tv.setHeight(280);
        tv.setText(text);
        tv.setId(id);
        tv.setGravity(Gravity.CENTER);
        tv.setWidth(width);
        GridLayout.LayoutParams p = new GridLayout.LayoutParams();
        p.setMargins(0,2,0,0);
        tv.setLayoutParams(p);
        tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.backgroundPlayerOverview, this.getTheme()));


        grid.addView(tv);
    }

    private void createRow(GridLayout grid, int i, SpreadSheet thisActivity){
        TextView tv = new TextView(thisActivity);
        tv.setText(Integer.toString(i));
        tv.setWidth(140);
        tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.staticNumbers, this.getTheme())); //without theme);
        tv.setTextSize(30);
        GridLayout.LayoutParams p = new GridLayout.LayoutParams();
        p.setMargins(0,2,2,2);
        p.height= ViewGroup.LayoutParams.MATCH_PARENT;
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        p.setGravity(Gravity.FILL);
        //p.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setLayoutParams(p);
        tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.staticNumbersBackground, this.getTheme()));
        grid.addView(tv);
    }

    private void createFields(GridLayout grid, int i, int j, SpreadSheet thisActivity){
        EditText editText = new EditText(thisActivity);
        editText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.cool_purple, this.getTheme()));
        editText.setText("");
        editText.setId(i+j*100);
        editText.setWidth((1080-140) / anzahlSpieler);
        editText.setSingleLine();
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setTextSize(40);
        editText.setHeight(170);
        editText.setMaxHeight(170);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setMargins(2,2,2,2);
        editText.setLayoutParams(params);
        editText.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.backgroundPlayerOverview, this.getTheme()));

        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SpreadSheet.this.setResults();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        grid.addView(editText);
    }

    private void startStatistics(View view) {
        /*
        Intent intent = new Intent(SpreadSheet.this,PlayerOverview.class);


        for(int spielernr=0;spielernr<anzahlSpieler;spielernr++) {
            ArrayList<Integer> listS1 = new ArrayList<>();
            for (int bahnnummer = 1; bahnnummer < anzahlbahnen + 1; bahnnummer++) {
                EditText x = SpreadSheet.this.findViewById(bahnnummer + spielernr * 100);

                try {
                    listS1.add(Integer.parseInt(String.valueOf(x.getText())));
                }catch (NumberFormatException e){
                    System.out.println("NaN");
                }
            }
            intent.putIntegerArrayListExtra("score"+spielernr,listS1);
        }

        intent.putStringArrayListExtra("SpielerNamen",spielerNamen);
        intent.putExtra("AnzahlSpieler",this.anzahlSpieler);
        intent.putExtra("AnzahlBahnen",this.anzahlbahnen);


        SpreadSheet.this.startActivity(intent);*/
    }

    void addIntensionColumn(GridLayout grid, String text, int width, boolean isFirst){
        TextView tv = new TextView(this);
        tv.setTextSize(22);
        //tv.setTextColor(getResources().getColor(R.color.cool_purple,this.getTheme()));
        tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.staticNumbers, this.getTheme()));
        tv.setPadding(0,20,0,0);
        tv.setHeight(200);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(text);
        tv.setWidth(width);
        GridLayout.LayoutParams p = new GridLayout.LayoutParams();

        if(!isFirst) {
            p.setMargins(2, 2, 2, 2);
        }
        else {
            p.setMargins(0, 2, 2, 2);
        }
        tv.setLayoutParams(p);
        tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.staticNumbersBackground, this.getTheme()));

        grid.addView(tv);
    }
}
