package com.webcheckers.model;

import java.util.Iterator;

/**
 * Game logic of Webcheckers.
 *
 * @author Michael Merlino
 * @author Truong Anh Tuan Hoang
 */
public class Game implements Iterable<Row>{
    private final Player redPlayer;
    private final Player whitePlayer;
    private final Board board;
    private final int ID;
    private boolean redTurn;
    private Player playerTurn;
    private Player currPlayer;
    private int whitePieces = 12;
    private int redPieces = 12;

    private boolean gameOver = false;

    public enum color {RED, WHITE, NONE}

    /**
     * Constructor of Game.
     * @param red player color
     * @param white opponent color
     */
    public Game(Player currPlayer, Player red, Player white)
    {
        this.currPlayer = currPlayer;
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new Board(redPlayer, whitePlayer);
        this.ID = redPlayer.getName().hashCode() * 31 + whitePlayer.getName().hashCode() * 67;
        this.redTurn = true;
    }

    /**
     * Get red player, this is the user.
     * @return red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Get white player, this is the opponent.
     * @return white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }
    public void setCurrPlayer(Player player) {
        this.currPlayer = player;
    }
    /**
     * Gets the player whose turn it currently is.
     * @return
     *      Red player on red's turn, White player on white's turn.
     */
    public Player getCurrentPlayer()
    {
        if (redTurn)
        {
            return redPlayer;
        }
        return whitePlayer;
    }
    /**
     * Gets current player color.
     * @return
     *      Red player on red's turn, White player on white's turn.
     */
    public Game.color getPlayerColor() {
        if (redTurn) {
            return color.RED;
        }
        return color.WHITE;
    }
    /**
     * Checks if player is white player, opponent.
     * @param player white player
     * @return true if player is white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(getWhitePlayer());
    }

    /**
     * Checks if player is red player, user.
     * @param player red player
     * @return true if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(getRedPlayer());
    }

    public boolean isPlayerInGame(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }
    /**
     * Gets the game session's ID.
     * @return game ID
     */
    public int getID()
    {
        return this.ID;
    }
    public boolean isGameOver() {
        return gameOver;
    }


    @Override
    public Iterator<Row> iterator() {
        if(currPlayer.equals(redPlayer)){
            this.board.setBoard(false);
            return this.board.getBoard().iterator();
        }
        this.board.setBoard(true);
        return this.board.getBoard().iterator();
    }

    /**
     * Set the turn to Red
     */
    public void setRedTurn(boolean status) {
       this.redTurn = true;
    }

    public int getNumRedPieces() {
        return redPieces;
    }

    public int getNumWhitePieces() {
        return whitePieces;
    }

    public boolean isRedTurn(Player player) {
        return redPlayer.equals(playerTurn);
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }


}
