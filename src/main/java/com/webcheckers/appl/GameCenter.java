package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.logging.Logger;

/**
 * Central communication of the game server for the model and UI.
 *
 * @author Truong Anh Tuan Hoang
 * @author Bin Qiu
 * @author Alex Johannesson
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

    // Attributes
    private final Lobby lobby;

    /**
     * Constructor of GameCenter
     */
    public GameCenter() {
        this.lobby = new Lobby();
    }

    /**
     * Gets the Lobby of the game.
     * @return lobby
     */
    public Lobby getLobby() {
        return this.lobby;
    }

    /**
     * Adds a player to the hashmap.
     * @param name name of player
     */
    public void addPlayer(String name) {
       this.lobby.addPlayer(name);
    }

    /**
     * Gets the name of the player.
     * @param name name of player
     * @return string name of player
     */
    public Player getPlayer(String name) {
        return this.lobby.getPlayer(name);
    }

    /**
     * Removes player from hashmap when signed out.
     * @param name name of player
     */
    public synchronized void removePlayer(String name) {
        lobby.removePlayer(name);
    }
}
