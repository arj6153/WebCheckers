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
    }

    /**
     * Adds a game to the master list
     * @param challenger
     *      The player that sent the game request.
     * @param recipient
     *      The player that received the game request.
     */
    public int addGame(Player challenger, Player recipient)
    {
        Game coolNewGame = new Game(challenger, recipient);
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

    /**
     * Adds a player to the hashmap  vm.put(VIEWMODE_PARAM, mode.PLAY);

      GameBoard board = gameCenter.getGame(gameId); //gets board via gameID
      board.isWhitePlayerBoard(!board.isRedPlayer(player));

      vm.put(REDPLAYER_PARAM, board.getRedPlayer());
      vm.put(WHITEPLAYER_PARAM, board.getWhitePlayer());
      vm.put(BOARD_PARAM, board);
      vm.put(ACTIVECOLOR_PARAM,board.getPlayerColor(board.getPlayerTurn()));
      if(board.isGameOver()){ // checks to see if the game is over
        modeOptions.put("isGameOver",board.isGameOver()); //sets the game over value into the map
        modeOptions.put("gameOverMessage",board.getGameOverMessage()); //stores the gameOver message into the map
        vm.put(MODEOPTIONS_PARAM, gson.toJson(modeOptions)); //converts the modeOptions map into a json
        board.getRedPlayer().setPlaying(false); //sets the red player to not be playing
        board.getWhitePlayer().setPlaying(false); //sets the white player to not be playing
        gameCenter.removeCurrentGame(gameId);
        gameCenter.addGameSave(gameId);
      }.
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

    public synchronized Game getGame(int gameID) {
       return this.gameMap.get(gameID);
    }

}
