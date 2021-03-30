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
     * Adds a game to the master list.
     *
     * @param redPlayer
     *      The player that sent the game request.
     * @param whitePlayer
     *      The player that received the game request.
     */
    public synchronized int addGame(Player redPlayer, Player whitePlayer)
    {
        Game newGame = new Game(redPlayer, redPlayer, whitePlayer);
        gameMap.put(newGame.getID(), newGame);
        redPlayer.setPlaying(true);
        whitePlayer.setPlaying(true);
        return newGame.getID();
    }

    /**
     * Gets the Lobby of the game.
     *
     * @return
     *      Lobby
     */
    public Lobby getLobby() {
        return this.lobby;
    }

    /**
     * Gets the hash map of the game board.
     *
     * @return
     *      Map game coordinates
     */
    public HashMap<Integer, Game> getGameMap() {
        return this.gameMap;
    }

    /**
     * Adds a player to the hashmap.
     *
     * @param name
     *      Name of player
     */
    public void addPlayer(String name) {
       this.lobby.addPlayer(name);
    }

    /**
     * Gets the name of the player.
     *
     * @param name
     *      Name of player
     * @return
     *      String name of player
     */
    public Player getPlayer(String name) {
        return this.lobby.getPlayer(name);
    }

    /**
     * Removes player from hashmap when signed out.
     *
     * @param name
     *      Name of player
     */
    public synchronized void removePlayer(String name) {
        lobby.removePlayer(name);
    }

    /**
     * Gets the current game.
     *
     * @param gameID
     *      ID of the game
     * @return
     *      Current game
     */
    public synchronized Game getGame(int gameID) {
       return this.gameMap.get(gameID);
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 759778c1b93d0059f3690341dff988dc65e4a000
    /**
     *
     * NEED DOCSTRING
     */
    public synchronized Game getGame(Player player) {
       for(Game game: this.gameMap.values())  {
           if( game.isPlayerInGame(player)) {
               return game;
           }
       }
       return null;
    }
=======
>>>>>>> parent of 9da3dab... implementing PostValidateMove and PostSubmitTurnRoute
<<<<<<< HEAD
=======
>>>>>>> parent of 9da3dab... implementing PostValidateMove and PostSubmitTurnRoute
=======
>>>>>>> 759778c1b93d0059f3690341dff988dc65e4a000
}
