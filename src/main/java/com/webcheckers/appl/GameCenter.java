package com.webcheckers.appl;

import java.util.HashMap;
import java.util.logging.Logger;

public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

    // Attributes
    private final Lobby lobby;

    public GameCenter() {
        this.lobby = new Lobby();
    }

    public Lobby getLobby() {
        return this.lobby;
    }
    public void addPlayer(String name) {
       this.lobby.addPlayer(name);
    }
    public Player getPlayer(String name) {
        return this.lobby.getPlayer(name);
    }

}
