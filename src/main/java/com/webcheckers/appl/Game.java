package com.webcheckers.appl;

import com.webcheckers.model.*;
import com.webcheckers.util.Message;

import java.lang.reflect.Array;
import java.util.*;

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
    private Move activeMove;
    private boolean gameOver;

    public enum Color {RED, WHITE, NONE}

    /**
     * Constructor of Game.
     *
     * @param red   player color
     * @param white opponent color
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
     * @return red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Get white player, this is the opponent.
     *
     * @return white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Gets current player color.
     *
     * @return Red player on red's turn, White player on white's turn.
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
     * @param player white player
     * @return true if player is white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(this.whitePlayer);
    }

    /**
     * Checks if player is red player, user.
     *
     * @param player red player
     * @return true if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(this.redPlayer);
    }

    /**
     * Checks if a given player object is participating in this game.
     *
     * @param player The player to check
     * @return True if the player is in the game, false otherwise
     */
    public boolean isPlayerInGame(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

    /**
     * Gets the game session's ID.
     *
     * @return game ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @return True if the game has ended, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }


    /**
     * @return True if it is currently the red player's turn
     */
    public boolean isRedTurn() {
        return playerTurn == redPlayer;
    }

    /**
     * @return The player whose turn it is
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
     * Get board
     *
     * @return the board
     */
    public BoardView getBoard() {
        return this.board;
    }

    /**
     * Get the number of pieces players has
     *
     * @param color: the color of the player to get num pieces from
     * @return The num pieces
     */
    public int getNumPieces(Color color) {
        if (color == Color.RED) {
            return this.redPieces;
        }
        return this.whitePieces;
    }

    /**
     *
     */
    public void setGameOver() {
        gameOver = true;
    }


    /**
     *
     * NEED DOCSTRING
     *
     *
     */
     public Message isValidMove(Move move) {
         activeMove = move;
         return new Message("valid", Message.Type.INFO);
     }

     public BoardView getRedBoardView() {
         List<Row> board = this.board.getBoard();
         List<Row> redBoard = new ArrayList<>(board);
         return new BoardView(redBoard);
     }
    public BoardView getWhiteBoardView() {
        List<Row> whiteBoard = new ArrayList<>(this.board.getBoard());
        Collections.reverse(whiteBoard);
        for(Row row : whiteBoard) {
            row.reverseSpace();
        }
        BoardView whiteBoardView = new BoardView(whiteBoard);
        return whiteBoardView;
    }
    public Move getActiveMove() {
        return activeMove;
    }
    public void move(Move move){
        int startRow = move.getStart().getRow();
        int startCell = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCell = move.getEnd().getCell();

        Space space = board.getRow(startRow).getSpace(startCell);
        Piece piece = space.getPiece();
        board.getRow(startRow).getSpace(startCell).setPiece(null);
        board.getRow(endRow).getSpace(endCell).setPiece(piece);
    }
}
