package com.webcheckers.model;

/**
 * Class for a Player containing the name and status.
 *
 * @author Alex Johannesson
 */
public class Player {

    private final String name;
    private boolean isPlaying = false;

    /**
     * Constructor for Player.
     *
     * @param name
     *      Name of player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     *
     * @return
     *      Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the two players' names are the same.
     *
     * @param otherPlayer
     *      Player being compared
     *
     * @return
     *      True if names are equal, false otherwise
     */
    public boolean equals(Player otherPlayer) {
        return this.name.equals(otherPlayer.name);
    }

    /**
     * @return
     *      Player Object Hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }


    /**
     * Sets the status of a player.
     *
     * @param status
     *      Playing if true, not playing if false
     */
    public void setPlaying(boolean status) {
        isPlaying = status;
    }

    /**
     * Checks if player is playing a game.
     *
     * @return
     *      True if playing, else false
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }
}
