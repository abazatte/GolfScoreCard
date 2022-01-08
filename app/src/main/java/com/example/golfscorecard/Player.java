package com.example.golfscorecard;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.golfscorecard.exceptions.HoleDoesntExistException;
import com.example.golfscorecard.exceptions.InvalidScoreException;

public class Player {
    private String name;
    private final Integer numberOfHoles;
    private final Integer maxSwings;
    private int[] scores;

    public Player(String name, Integer numberOfHoles, Integer maxSwings) {
        this.name = name;
        this.numberOfHoles = numberOfHoles;
        this.maxSwings = maxSwings;
        scores = new int[numberOfHoles + 1];
        //in java new arrays are initialized with 0 so should be fine
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int[] getScores() {
        return scores;
    }

    private void updateSum() {
        Integer sum = 0;
        //add everything except last row!
        for (int i = 0; i < (scores.length - 1); i++) {
            sum += scores[i];
        }
        scores[scores.length - 1] = sum;
    }

    /**
     * takes the real world Golf Hole (start at 1) and does -1
     * so that we can begin at the start of the array!
     * throws exceptions when getting unrealistic values
     */
    public void updateHole(Integer hole, Integer score) throws HoleDoesntExistException, InvalidScoreException {
        if (hole <= 0 || hole > numberOfHoles) {
            throw new HoleDoesntExistException("hole doesnt exist in the Course!");
        }
        if (score <= 0 || score > maxSwings) {
            throw new InvalidScoreException("the score is not allowed");
        }
        scores[hole - 1] = score;
        updateSum();
    }

}
/*
CODE GRAVEYARD:
package com.example.golfscorecard;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.golfscorecard.exceptions.HoleDoesntExistException;
import com.example.golfscorecard.exceptions.InvalidScoreException;

public class Player implements Parcelable {
    private String name;
    private final Integer numberOfHoles;
    private final Integer maxSwings;
    private int[] scores;

    public Player(String name, Integer numberOfHoles, Integer maxSwings) {
        this.name = name;
        this.numberOfHoles = numberOfHoles;
        this.maxSwings = maxSwings;
        scores = new int[numberOfHoles + 1];
        //in java new arrays are initialized with 0 so should be fine
    }

    protected Player(Parcel in) {
        name = in.readString();
        numberOfHoles = in.readInt();
        maxSwings = in.readInt();
        scores = new int[numberOfHoles + 1];
        in.readIntArray(scores);
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int[] getScores() {
        return scores;
    }

    private void updateSum() {
        Integer sum = 0;
        //add everything except last row!
        for (int i = 0; i < (scores.length - 1); i++) {
            sum += scores[i];
        }
        scores[scores.length - 1] = sum;
    }

public void updateHole(Integer hole, Integer score) throws HoleDoesntExistException, InvalidScoreException {
    if (hole <= 0 || hole > numberOfHoles) {
        throw new HoleDoesntExistException("hole doesnt exist in the Course!");
    }
    if (score <= 0 || score > maxSwings) {
        throw new InvalidScoreException("the score is not allowed");
    }
    scores[hole - 1] = score;
    updateSum();
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(numberOfHoles);
        parcel.writeInt(maxSwings);
        parcel.writeIntArray(scores);
    }
}

 */