package com.webcheckers.appl;

import com.webcheckers.model.*;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Central communication of the game server for the model and UI.
 *
 * @author Truong Anh Tuan Hoang
 * @author Bin Qiu
 * @author Alex Johannesson
 * @author Michael Merlino
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());

    // Attributes
    private HashMap<Integer, Game> gameMap;
    private final Lobby lobby;

    /**
     * Constructor of GameCenter
     */
    public GameCenter() {
        this.lobby = new Lobby();
        this.gameMap = new HashMap<>();
    }

    /**
     * Adds a game to the master list
     * @param challenger
     *      The player that sent the game request.
     * @param recipient
     *      The player that received the game request.
     */
    public synchronized int addGame(Player challenger, Player recipient)
    {
        Game coolNewGame = new Game(challenger, challenger, recipient);
        System.out.println(coolNewGame.getID());
        gameMap.put(coolNewGame.getID(), coolNewGame);
        challenger.setPlaying(true);
        recipient.setPlaying(true);
        coolNewGame.setRedTurn(true);
        return coolNewGame.getID();
    }

    /**
     * Gets the Lobby of the game.
     * @return lobby
     */
    public Lobby getLobby() {
        return this.lobby;
    }


    public HashMap<Integer, Game> getGameMap() {
        return this.gameMap;
    }
    /**
     * Adds a player to the hashmap
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

    /**
     * Gets the current game.
     * @param gameID ID of the game
     * @return current game
     */
    public synchronized Game getGame(int gameID) {
       return this.gameMap.get(gameID);
    }

}
