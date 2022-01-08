package com.example.golfscorecard;



import com.example.golfscorecard.exceptions.SadPlayerException;

import java.util.ArrayList;
import java.util.List;

public class Scorecard {
    private String courseName;
    private Integer numberOfHoles;
    private Integer maxSwings;
    private ArrayList<String> playerList;

    //dunno how tf i would init the player List tbh lol, maybe just add em later!

    public Scorecard(String courseName, Integer numberOfHoles, Integer maxSwings){
        this.courseName = courseName;
        this.numberOfHoles = numberOfHoles;
        this.maxSwings = maxSwings;

        this.playerList = new ArrayList<>();
    }


    public void setCourseName(String c){
        this.courseName = c;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public void addPlayer(String name) throws SadPlayerException {
        if(name == null){
            throw new SadPlayerException("pls give it a name at least!");
        }
        Player player = new Player(name, this.numberOfHoles, this.maxSwings);
        playerList.add(name);
    }

    public int getAnzahl(){
        return playerList.size();
    }

    public ArrayList getPlayerlist(){
        return this.playerList;
    }
}
