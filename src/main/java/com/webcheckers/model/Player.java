package com.webcheckers.model;

/**
 * Class for a Player containing the name and status.
 *
 * @author Alex Johannesson
 */
public class Player {

    private final String name;

    /**
     * Constructor for Player
     *
     * @param
     *      name name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name
     *
     * @return
     *      name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the two players' names are the same.
     *
     * @param otherPlayer
     *      player being compared
     *
     * @return
     *      true if names are equal, false otherwise
     */
    public boolean equals(Player otherPlayer) {
        return this.name.equals(otherPlayer.name);
    }

    private boolean isPlaying = false;

    /**
     * Sets the status of a player.
     *
     * @param status
     *      playing if true, not playing if false
     */
    public void setPlaying(boolean status) {
        isPlaying = status;
    }

    /**
     * Checks if player is playing a game.
     *
     * @return
     *      true if playing, else false
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }
}
