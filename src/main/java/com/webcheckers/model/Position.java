package com.webcheckers.model;

/**
 * Class for the position on the game board.
 *
 * @author Truong Anh Tuan Hoang
 * @author Alex Johannesson
 */
public class Position  {

    private final int row;
    private final int cell;

    /**
     * Constructor for the position.
     *
     * @param row
     *      The row
     * @param cell
     *      The cell
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * @return
     *      The row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return
     *      The cell
     */
    public int getCell() {
        return this.cell;
    }

    public boolean equal(Position other) {
        return this.row == other.row && this.cell == other.cell;
    }

    public String toString() {
        return this.row+" "+this.cell;
    }
}