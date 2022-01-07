package com.example.golfscorecard;



import android.os.Parcel;
import android.os.Parcelable;

import com.example.golfscorecard.exceptions.SadPlayerException;

import java.util.ArrayList;
import java.util.List;

public class Scorecard implements Parcelable {
    public String courseName;
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

    protected Scorecard(Parcel in) {
        courseName = in.readString();
        if (in.readByte() == 0) {
            numberOfHoles = null;
        } else {
            numberOfHoles = in.readInt();
        }
        if (in.readByte() == 0) {
            maxSwings = null;
        } else {
            maxSwings = in.readInt();
        }
    }

    public void setCourseName(String c){
        this.courseName = c;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public static final Creator<Scorecard> CREATOR = new Creator<Scorecard>() {
        @Override
        public Scorecard createFromParcel(Parcel in) {
            return new Scorecard(in);
        }

        @Override
        public Scorecard[] newArray(int size) {
            return new Scorecard[size];
        }
    };

    public void addPlayer(Player player) throws SadPlayerException {
        if(player == null){
            throw new SadPlayerException("pls give it a name at least!");
        }
        playerList.add(player);
    }

    public int getAnzahl(){
        return playerList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseName);
        if (numberOfHoles == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numberOfHoles);
        }
        if (maxSwings == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maxSwings);
        }
    }
}
