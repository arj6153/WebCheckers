package com.webcheckers.model;

/**
 * Class containing the Spaces of the checker board.
 *
 * @author Truong Anh Tuan Hoang
 */
public class Space {

    private final int cellIdx;
    private Piece piece;
    private boolean valid;

    /**
     * Constructor of Space.
     *
     * @param cellIdx
     *      Column number of the game board
     * @param currentPiece
     *      Current checker piece
     */
    public Space(int cellIdx, Piece currentPiece, boolean valid) {
        this.valid = valid;
        this.cellIdx = cellIdx;
        this.piece = currentPiece;
    }

    /**
     * @return
     *      The column number on the game board
     */
    public int getCellIdx() {
        return this.cellIdx;
    }

    /**
     * Sets a checker piece on an empty space.
     *
     * @param piece
     *      The current checker piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;

    }

    /**
     * Checks if the space is valid.
     *
     * @return
     *      True if the space is black and there is not piece already there,
     *      otherwise false
     */
    public boolean isValid() {
        return valid && piece == null;
    }

    /**
     * @return
     *      The current piece on the space of the current board
     */
    public Piece getPiece() {
        return this.piece;
    }
}