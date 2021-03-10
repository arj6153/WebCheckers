package com.webcheckers.appl;

import java.util.HashMap;

/**
 * Class contains all the active players on the WebChecker Server
 * @author: Truong Anh Tuan Hoang
 *
 */
public class Lobby {

    // lobby holds the username of a player as a key and the Player as the value
    private HashMap<String, Player> lobby;

    public Lobby() {
        this.lobby = new HashMap<>();
    }

    // Public Methods

    public synchronized void addPlayer(String name) {
        Player player = new Player(name);
        this.lobby.put(name, player);
    }

    public synchronized  boolean playerExists(String name) {
        return this.lobby.containsKey(name);
    }
}
