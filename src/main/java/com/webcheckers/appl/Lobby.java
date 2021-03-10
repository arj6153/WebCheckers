package com.webcheckers.appl;

import java.util.HashMap;
import java.util.UUID;

/**
 * Class contains all the active players on the WebChecker Server
 * @author: Truong Anh Tuan Hoang
 * @author: Alex Johannesson
 *
 */
public class Lobby {


    // lobby holds the username of a player as a key and the Player as the value
    private HashMap<String, Player> lobby;
    private String error = "<p> There are no players available at this time </p>";


    public Lobby() {

        this.lobby = new HashMap<>();
    }

    // Public Methods



    public synchronized void addPlayer(String name) {
        Player player = new Player(name);
        this.lobby.put(name, player);

    }

    public synchronized Player getPlayer(String name) {
       return this.lobby.get(name);
    }

    public synchronized HashMap<String,Player> getMap () {
        return this.lobby;
    }

    public synchronized int getLobbySize() {
        return this.lobby.size();
    }

    public synchronized void removePlayer(String name) {
        this.lobby.remove(name);
    }

    public synchronized boolean playerExists(String name) {
        return this.lobby.containsKey(name);
    }
}
