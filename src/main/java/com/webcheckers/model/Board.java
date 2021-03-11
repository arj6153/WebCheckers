package com.webcheckers.model;

import java.util.*;
import java.util.List;
import java.util.Iterator;

import static com.webcheckers.model.Game.Color.RED;
import static com.webcheckers.model.Game.Color.WHITE;

/**
 * Class that contains the board, tiles, and checker pieces.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 * @author: Michael Merlino
 */
public class Board {
    private List<Row> board;

    private Player redPlayer;
    private Player whitePlayer;

    private List<Row> redBoard = new ArrayList<>();
    private List<Row> whiteBoard = new ArrayList<>();
    /**
     * Constructor of the checker board.
     */
    public Board(Player redPlayer, Player whitePlayer) {
       this.redPlayer = redPlayer;
       this.whitePlayer = whitePlayer;
       this.board = new ArrayList<>();
       initializeBoard(RED);
       initializeBoard(WHITE);

    }
    /**
     * Checks if a tile is occupied by a checker piece.
     * @param x  the X coordinate of the tile
     * @param y the Y coordinate of the tile
     * @return true if there is no checker on the tile, false if there is one
     */
    private boolean isTileEmpty(int x, int y)
    {
        return false;
    }

    /**
     * Retrieve a piece (if any) from the given x/y coordinates.
     * @return The piece if there is one present, null if none exists.
     */
    public Piece getTile(int x, int y) {
        return null;
    }

    /**
     * Given the x,y coordinates of a piece and a target location, determines if the piece can be dropped at the target.
     * @param pieceX the X location of the selected piece
     * @param pieceY the Y location of the selected piece
     * @param targetX the X location of the target tile
     * @param targetY the Y location of the target tile
     * @return true if the piece can be dropped at the target, false is not
    public boolean isDroppable(int pieceX, int pieceY, int targetX, int targetY)
    {
        // Is the target tile white or occupied? If so, not droppable
        // Is the tile where the selected piece should be actually occupied by a piece? If not, not droppable
        return !isWhiteTile(targetX, targetY) && isTileEmpty(targetX, targetY) && !isTileEmpty(pieceX, pieceY);
    }

     */

    /**
     * Create a new standard board based on checkers rules.
     * @return a new board, as an array of piece enums
     */
    public void initializeBoard(Game.Color color) {
        List<Row> board = null;
        if (color == RED) {
           board = this.redBoard;
        } else if (color == WHITE) {
            board = this.whiteBoard;
        }
        boolean flag = false;
        for( int row = 0; row < 8; row++) {
            if (row <= 2) {
                if (color == RED) {
                    board.add(row, new Row(row, WHITE, flag));
                } else if(color == WHITE) {
                    board.add(row, new Row(row, RED, flag));
                }
            } else if (row >= 5) {
                if (color == RED) {
                    board.add(row, new Row(row, RED, flag));
                } else if(color == WHITE) {
                    board.add(row, new Row(row, WHITE, flag));
                }
            } else {
                board.add(row, new Row(row, null, flag));
            }
            flag = !flag;
        }
    }

    /**
     * Checks if move is within boundary of the board.
     * @param row the row coordinate
     * @param col the column coordinate
     * @return true if move is within board boundary, else false
     */
    public boolean isValid(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }
    public void setBoard(boolean whiteboard) {
        if (whiteboard) {
            this.board = whiteBoard;
        } else {
            this.board = redBoard;
        }
    }

    public List<Row> getRedBoard() {
        return redBoard;
    }

    public List<Row> getWhiteBoard() {
        return whiteBoard;
    }

}