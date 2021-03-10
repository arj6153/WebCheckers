package com.webcheckers.appl;

import java.util.logging.Logger;

public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

    // Attributes
    private final Lobby lobby;

    public GameCenter() {
        this.lobby = new Lobby();
    }
}
