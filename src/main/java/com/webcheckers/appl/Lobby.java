package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;

/**
 * Class contains all the active players on the WebChecker Server.
 *
 * @author: Truong Anh Tuan Hoang
 * @author: Alex Johannesson
 * @author: Bin Qiu
 */
public class Lobby {

    // lobby holds the username of a player as a key and the Player as the value
    private HashMap<String, Player> map;
    private String error = "<p> There are no players available at this time </p>";

    /**
     * Constructor of the lobby holding the hashmap.
     */
    public Lobby() {
        this.map = new HashMap<>();
    }

    /**
     * Add player to hashmap.
     * @param name player name
     */
    public synchronized void addPlayer(String name) {
        Player player = new Player(name);
        this.map.put(name, player);
    }

    /**
     * Gets player in hashmap.
     * @param name player name
     * @return player
     */
    public synchronized Player getPlayer(String name) {
       return this.map.get(name);
    }

    /**
     * Hashmap of the players and their names.
     * @return hashmap
     */
    public synchronized HashMap<String,Player> getMap () {
        return this.map;
    }

    /**
     * Gets the size of the hashmap.
     * @return lobby size
     */
    public synchronized int getLobbySize() {
        return this.map.size();
    }

    /**
     * Removes player from hashmap.
     * @param name player name
     */
    public synchronized void removePlayer(String name) {
        this.map.remove(name);
    }

    /**
     * Checks if player exists in hashmap.
     * @param name player name
     * @return true if player exists, else false
     */
    public synchronized boolean playerExists(String name) {
        return this.map.containsKey(name);
    }
}
