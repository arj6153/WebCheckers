package com.webcheckers.appl;

import com.webcheckers.model.*;
import java.util.HashMap;

/**
 * Central communication of the game server for the model and UI.
 *
 * @author Truong Anh Tuan Hoang
 * @author Bin Qiu
 * @author Alex Johannesson
 * @author Michael Merlino
 */
public class GameCenter {

    private final HashMap<Integer, Game> gameMap;
    private final Lobby lobby;
    private int aiNum = 0;

    /**
     * Constructor of GameCenter.
     */
    public GameCenter() {
        this.lobby = new Lobby();
        this.gameMap = new HashMap<>();
    }

    /**
     * Adds a game to the game map.
     *
     * @param redPlayer
     *      The player that sends the game request
     * @param whitePlayer
     *      The player that receives the game request
     */
    public synchronized int addGame(Player redPlayer, Player whitePlayer) {
        Game newGame = new Game(redPlayer, redPlayer, whitePlayer);
        gameMap.put(newGame.getID(), newGame);
        redPlayer.setPlaying(true);
        whitePlayer.setPlaying(true);
        lobby.removePlayer(redPlayer.getName());
        lobby.removePlayer(whitePlayer.getName());
        return newGame.getID();
    }

    /**
     * @return
     *      The lobby of the active players
     */
    public synchronized Lobby getLobby() {
        return this.lobby;
    }

    /**
     * @return
     *      The map of the games in session
     */
    public synchronized HashMap<Integer, Game> getGameMap() {
        return this.gameMap;
    }

    /**
     * Adds a player to the lobby.
     *
     * @param name
     *      The name of the player
     */
    public synchronized void addPlayer(String name) {
       this.lobby.addPlayer(name);
    }

    /**
     * Gets the name of the player from the lobby.
     *
     * @param name
     *      The name of player
     * @return
     *      The player
     */
    public synchronized Player getPlayer(String name) {
        return this.lobby.getPlayer(name);
    }

    /**
     * Removes player from the lobby.
     *
     * @param name
     *      The player name
     */
    public synchronized void removePlayer(String name) {
        lobby.removePlayer(name);
    }

    /**
     * Get the game using the player.
     *
     * @param player
     *      The player
     * @return
     *      The game containing the player
     */
    public synchronized Game getGame(Player player) {
       for(Game game: this.gameMap.values())  {
           if( game.isPlayerInGame(player)) {
               return game;
           }
       }
       return null;
    }

    /**
     * Gets the current game using the gameID.
     *
     * @param gameID
     *      The ID of the game
     * @return
     *      The game associated to the gameID
     */
    public synchronized Game getGame(int gameID) {
        return this.gameMap.get(gameID);
    }

    /**
     * Gets the opposing player of teh game.
     *
     * @param player
     *      The current player
     * @return
     *      The opposing player
     */
    public synchronized Player getOpponent(Player player) {
        Game game = getGame(player);
        if(game.getRedPlayer().equals(player)) {
            return game.getWhitePlayer();
        }
        return game.getRedPlayer();
    }
    public synchronized int getAiNum() {
        return aiNum;
    }
    public synchronized void addAiNum() {
        aiNum++;
    }
}