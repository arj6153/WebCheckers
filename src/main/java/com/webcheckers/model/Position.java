package com.webcheckers.model;

public class Position  {
    private final int row;
    private final int cell;

    /**
     * Instantiates row and position parameters
     *
     * @param row the row
     * @param cell the cell
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Gets the row
     *
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the Cell
     *
     * @return cell
     */
    public int getCell() {
        return this.cell;
    }
}
