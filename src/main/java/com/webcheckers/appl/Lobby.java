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

    private final HashMap<String, Player> map;

    /**
     * Constructor of the lobby holding the hashmap.
     */
    public Lobby() {
        this.map = new HashMap<>();
    }

    /**
     * Add player to hashmap.
     *
     * @param name
     *      The player name
     */
    public synchronized void addPlayer(String name) {
        Player player = new Player(name);
        this.map.put(name, player);
    }

    /**
     * Gets a player in hashmap.
     *
     * @param name
     *      The player name
     * @return
     *      A player
     */
    public synchronized Player getPlayer(String name) {
       return this.map.get(name);
    }

    /**
     * @return
     *      The hashmap of the players using their names as the key
     */
    public synchronized HashMap<String,Player> getMap () {
        return this.map;
    }

    /**
     * @return
     *      The lobby size of the hashmap
     */
    public synchronized int getLobbySize() {
        return this.map.size();
    }

    /**
     * Removes player from hashmap.
     *
     * @param name
     *      The player name
     */
    public synchronized void removePlayer(String name) {
        this.map.remove(name);
    }

    /**
     * Checks if player exists in the hashmap.
     *
     * @param name
     *      The player name
     * @return
     *      True if player exists in the hashmap, else false
     */
    public synchronized boolean playerExists(String name) {
        return this.map.containsKey(name);
    }
}


