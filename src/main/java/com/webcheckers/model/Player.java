package com.webcheckers.model;

import com.webcheckers.appl.Lobby;

import java.util.HashMap;

public class Player extends Lobby {

    private final String name;

    public Player(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    private boolean isPlaying = false;

    public void setPlaying(boolean status) {
        isPlaying = status;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }




}
