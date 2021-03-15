package com.webcheckers.model;

import java.util.*;
import java.util.List;

import static com.webcheckers.model.Game.color.*;

/**
 * Class that contains the board, tiles, and checker pieces.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 * @author: Michael Merlino
 */
public class Board {
    private List<Row> board;

    private final Player redPlayer;
    private final Player whitePlayer;

    private final List<Row> redBoard = new ArrayList<>();
    private final List<Row> whiteBoard = new ArrayList<>();

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
     * Create a new standard board based on checkers rules.
     *
     * @return
     *      a new board, as an array of piece enums
     */
    public void initializeBoard(Game.color color) {
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
                board.add(row, new Row(row, color, flag));
            } else {
                board.add(row, new Row(row, NONE, flag));
            }
            flag = !flag;
        }
    }

    /**
     * Set up the white player's board, else set up the red player's board.
     *
     * @param whiteboard
     *      true if the board is the whiteboard, false if the board is the redboard
     */
    public void setBoard(boolean whiteboard) {
       if (whiteboard) {
            this.board = whiteBoard;
        } else {
            this.board = redBoard;
        }
    }

    /**
     * Gets the checker board.
     *
     * @return
     *      checker board
     */
    public List<Row> getBoard() {
        return board;
    }
}