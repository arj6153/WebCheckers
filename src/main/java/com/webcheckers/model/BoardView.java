package com.webcheckers.model;

import java.util.*;
import java.util.List;

import static com.webcheckers.appl.Game.Color.*;

/**
 * Class that contains the board, tiles, and checker pieces.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 * @author: Michael Merlino
 */
public class BoardView implements Iterable<Row>{
    private final List<Row> board;
    static final int DIM= 8;
    /**
     * Constructor of the checker board.
     */
    public BoardView() {
       this.board = new ArrayList<>();
       initializeBoard();
    }

    /**
     * Create a new standard board based on checkers rules.
     */
    public void initializeBoard() {
        boolean flag = false;
        for( int row = DIM-1; row >= 0; row--) {
            if (row <= 2) {
                board.add(new Row(row, RED, flag));
            } else if (row >= 5) {
                board.add(new Row(row, WHITE, flag));
            } else {
                board.add(new Row(row, NONE, flag));
            }
            flag = !flag;
        }
    }

    /**
     * Gets the checker board.
     *
     * @return
     *      Checker board
     */
    public List<Row> getBoard() {
        return board;
    }

    /**
     * Given index, get the row from boards.
     *
     * @param rowIdx
     *      Index of the row
     * @return
     *      The specified row
     */
    public Row getRow(int rowIdx) {
        if (rowIdx >= DIM || rowIdx < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.board.get(rowIdx);
    }

    /**
     * Iterates the board.
     *
     * @return
     *      Iterator for the board
     */
    public Iterator<Row> iterator() {
        return this.board.iterator();
    }
}