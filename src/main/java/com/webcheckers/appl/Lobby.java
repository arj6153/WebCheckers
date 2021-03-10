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


    public void playerLogout(Player player) {
        this.lobby.remove(player.getName());
    }

    public synchronized int getLobbySize() {
        return this.lobby.size();
    }
    public String listPlayers(Player player) {
        if(this.lobby.size() <= 1) {
            return error;

        }
        String lobbyPlayers = "";
        for(String players : this.lobby.keySet()) {
            Player p = this.lobby.get(players);
            if(p != player) { // && (gameNotActive == null)) add once game is setup
                return error;
            }
            lobbyPlayers += "<ol> <li class='players'> <div class='list'> <p> <a href='/game/" + p.getName() + '>' + players + " </p> </div> </li> </ol>";
        }
        return lobbyPlayers;
    }


    public synchronized boolean playerExists(String name) {
        return this.lobby.containsKey(name);
    }
}
