package com.webcheckers.model;

import com.webcheckers.appl.Lobby;

import java.util.HashMap;

/**
 * Class for a Player containing the name and status.
 */
public class Player extends Lobby {

    private final String name;

    /**
     * Constructor for Player
     * @param name name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name
     * @return name of player
     */
    public String getName() {
        return name;
    }

    public boolean equals(Player otherPlayer) {
        return this.name.equals(otherPlayer.name);
    }
    private boolean isPlaying = false;

    /**
     * Sets the status of a player.
     * @param status playing if true, not playing if false
     */
    public void setPlaying(boolean status) {
        isPlaying = status;
    }

    /**
     * Checks if player is playing a game.
     * @return true if playing, else false
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }
}
