package com.webcheckers.model;

import com.webcheckers.appl.Game;

import java.lang.reflect.Array;
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

    public BoardView(List<Row> board) {
       this.board = board;
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
     *      checker board
     */
     public List<Row> getBoard() {
        return board;
    }

    /**
     * Given index, get the row from boards
     * @param rowIdx of the row
     * @return
     *      the specified row
     */
    public Row getRow(int rowIdx) {
        if (rowIdx >= DIM || rowIdx < 0) {
            throw new IndexOutOfBoundsException();
        }
        for(Row row: board) {
            if (row.getIndex() == rowIdx) {
                return row;
            }
        }
        return null;
    }

    /**
     * @return
     *      Iterator for the board
     */
    public Iterator<Row> iterator() {
        return this.board.iterator();
    }

    public void printBoard() {
        for(Row row: board) {
            System.out.print(row.getIndex()+" ");
            for(Space space: row) {
                if(space.getPiece() == null) {
                    System.out.print(". ");
                } else if(space.getPiece().getColor()==RED) {
                    System.out.print("R ");
                } else if (space.getPiece().getColor()== WHITE) {
                    System.out.print("W ");
                }
            }
            System.out.println();
        }
    }
}