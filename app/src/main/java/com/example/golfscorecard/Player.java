package com.example.golfscorecard;


import com.example.golfscorecard.exceptions.HoleDoesntExistException;
import com.example.golfscorecard.exceptions.InvalidScoreException;

public class Player {
    private String name;
    private final Integer numberOfHoles;
    private final Integer maxSwings;
    private Integer[] scores;

    public Player(String name, Integer numberOfHoles, Integer maxSwings){
        this.name = name;
        this.numberOfHoles = numberOfHoles;
        this.maxSwings = maxSwings;
        scores = new Integer[numberOfHoles+1];
        //in java new arrays are initialized with 0 so should be fine
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Integer[] getScores() {
        return scores;
    }

    private void updateSum(){
        Integer sum = 0;
        //add everything except last row!
        for(int i = 0; i<(scores.length-1);i++){
            sum += scores[i];
        }
        scores[scores.length-1] = sum;
    }

    /** takes the real world Golf Hole (start at 1) and does -1
     * so that we can begin at the start of the array!
     * throws exceptions when getting unrealistic values
     * */
    public void updateHole(Integer hole, Integer score) throws HoleDoesntExistException, InvalidScoreException {
        if(hole <= 0 || hole > numberOfHoles){
            throw new HoleDoesntExistException("hole doesnt exist in the Course!");
        }
        if(score <= 0 || score > maxSwings){
            throw new InvalidScoreException("the score is not allowed");
        }
        scores[hole-1] = score;
        updateSum();
    }
}
