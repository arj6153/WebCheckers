package com.webcheckers.model;

/**
 * Game logic of Webcheckers.
 *
 * @author Michael Merlino
 */
public class Game {
    private Player redPlayer;
    private Player whitePlayer;
    private Board board;
    private int ID;
    private boolean redTurn;

    /**
     * Constructor of Game.
     * @param red player color
     * @param white opponent color
     */
    public Game(Player red, Player white)
    {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new Board();
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
        else
        {
            return whitePlayer;
        }
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

    /**
     * Gets the game session's ID.
     * @return game ID
     */
    public int getID()
    {
        return this.ID;
    }

    /**
     * Get the board of the current instance
     * @return current board
     */
    public Board getBoard()
    {
        return this.board;
    }
}
