package com.example.golfscorecard.exceptions;

public class HoleDoesntExistException extends Exception{
    public HoleDoesntExistException(String message){
        super(message);
    }
}
