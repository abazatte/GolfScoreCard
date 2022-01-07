package com.example.golfscorecard;



import com.example.golfscorecard.exceptions.SadPlayerException;

import java.util.ArrayList;
import java.util.List;

public class Scorecard {
    private String courseName;
    private Integer numberOfHoles;
    private Integer maxSwings;
    private List<Player> playerList;

    //dunno how tf i would init the player List tbh lol, maybe just add em later!

    public Scorecard(String courseName, Integer numberOfHoles, Integer maxSwings){
        this.courseName = courseName;
        this.numberOfHoles = numberOfHoles;
        this.maxSwings = maxSwings;

        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) throws SadPlayerException {
        if(player == null){
            throw new SadPlayerException("pls give it a name at least!");
        }
        playerList.add(player);
    }
}
