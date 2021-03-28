package com.webcheckers.appl;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;

/**
 * Game logic of Webcheckers.
 *
 * @author Michael Merlino
 * @author Truong Anh Tuan Hoang
 */
public class Game {
    private final Player redPlayer;
    private final Player whitePlayer;
    private final BoardView board;
    private final int ID;
    private Player playerTurn;
    private int redPieces = 12;
    private int whitePieces = 12;
    private boolean gameOver;

    public enum Color {RED, WHITE, NONE}

    /**
     * Constructor of Game.
     *
     * @param red
     *      Player color
     * @param white
     *      Opponent color
     */
    public Game(Player playerTurn, Player red, Player white) {
        this.playerTurn = playerTurn;
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = new BoardView();
        this.ID = redPlayer.hashCode() * 31 + whitePlayer.hashCode() * 67;
        gameOver = false;
    }

    /**
     * Get red player, this is the user.
     *
     * @return
     *      Red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Get white player, this is the opponent.
     *
     * @return
     *      White player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Gets current player color.
     *
     * @return
     *      Red player on red's turn, White player on white's turn.
     */
    public Color getPlayerColor() {
        if (playerTurn == redPlayer) {
            return Color.RED;
        } else if (playerTurn == whitePlayer) {
            return Color.WHITE;
        }
        return null;
    }

    /**
     * Checks if player is white player, opponent.
     *
     * @param player
     *      White player
     * @return
     *      True if player is white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(this.whitePlayer);
    }

    /**
     * Checks if player is red player, user.
     *
     * @param player
     *      Red player
     * @return
     *      True if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(this.redPlayer);
    }

    /**
     * Checks if a given player object is participating in this game.
     *
     * @param player
     *      The player to check
     * @return
     *      True if the player is in the game, false otherwise
     */
    public boolean isPlayerInGame(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

    /**
     * Gets the game session's ID.
     *
     * @return
     *      Game ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Boolean for checking if the game is over.
     *
     * @return
     *      True if the game has ended, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Boolean for checking if it is the red player's turn.
     *
     * @return
     *      True if it is currently the red player's turn
     */
    public boolean isRedTurn() {
        return playerTurn == redPlayer;
    }

    /**
     * Finds whose turn it is.
     *
     * @return
     *      The player whose turn it is
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Sets which player's turn it is.
     */
    public void setPlayerTurn(Player player) {
        this.playerTurn = player;
    }

    /**
     * Get the board.
     *
     * @return
     *      The board
     */
    public BoardView getBoard() {
        return this.board;
    }

    /**
     * Get the number of pieces players has
     *
     * @param color
     *      The color of the player to get num pieces from
     * @return
     *      The number of pieces
     */
    public int getNumPieces(Color color) {
        if (color == Color.RED) {
            return this.redPieces;
        }
        return this.whitePieces;
    }

    /**
     * Sets the status of the game to game over.
     */
    public void setGameOver() {
        gameOver = true;
    }
}