package com.webcheckers.model;

/**
 * Class containing the Tiles of the checker board.
 */
public class Tile {

    private int cellIdx;
    private Piece piece;
    private Status status;

    public enum Status {
        EMPTY,
        FILLED,
        INVALID
    }

    private boolean valid;

    /**
     * Constructor of Tile.
     * @param cellIdx cell information
     * @param currentPiece current checker piece
     */
    public Tile(int cellIdx, Piece currentPiece) {
        this.cellIdx = cellIdx;
        if (currentPiece == null) {
            this.piece = null;
            this.status = Status.EMPTY;
        } else {
            this.piece = currentPiece;
            this.status = Status.FILLED;
        }
    }

    /**
     * Gets cell column.
     * @return column number
     */
    public int getCellIdx() {
        return this.cellIdx;
    }

    /**
     * Gets the status of a tile.
     * @return status
     */
    public Status getTile() {
        return this.status;
    }

    /**
     * Sets a checker piece on an empty tile.
     * @param piece current checker piece
     * @return status of checker piece
     */
    public Status setPiece(Piece piece) {
        if (status == Status.EMPTY) {
            this.piece = piece;
            this.status = Status.FILLED;
            return this.status;
        }
        return this.status;
    }

    /**
     * Checks if the tile is valid.
     * @return true if the tile is black and there is not piece already there,
     *         otherwise false
     */
    public boolean isValid() {
        return valid && piece == null;

    }

    /**
     * Checks status of a tile to see if it is occupied.
     * @return true if tile is occupied, else false
     */
    public boolean isFilled() {
        return this.status == Status.FILLED;
    }

    /**
     * Checks status of a tile to see if it is empty.
     * @return true if the tile is empty, else false
     */
    public boolean isEmpty() {
        return this.status == Status.EMPTY;
    }

    /**
     * Gets the checker piece on the tile.
     * @return checker piece
     */
    public Piece getPiece() {
        return this.piece;
    }
}
