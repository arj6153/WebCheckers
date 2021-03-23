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
        for( int row = 7; row >= 0; row--) {
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
     *      checker board
     */
    public List<Row> getBoard() {
        return board;
    }

    /**
     * Given index, get the row from boards
     * @param rowIdx of the row
     * @return
     *      the specifed row
     */
    public Row getRow(int rowIdx) {
        return this.board.get(rowIdx);
    }

    /**
     * @return
     *      Iterator for the board
     */
    public Iterator<Row> iterator() {
        return this.board.iterator();
    }
}