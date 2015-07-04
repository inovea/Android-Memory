package com.example.oussama.smartmemory.model;

import java.io.Serializable;

/**
 * Created by Oussama on 01/07/2015.
 */
public class Player {

    String username;
    int time;
    int counter;
    int score;

    public Player() {
    }

    public Player(String username, int time, int counter, int score) {
        this.username = username;
        this.time = time;
        this.counter = counter;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
