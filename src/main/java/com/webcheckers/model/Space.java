package com.webcheckers.model;
/**
 * Class containing the Tiles of the checker board.
 *
 * @author Truong Anh Tuan Hoang
 */
public class Space {

    private int cellIdx;
    private Piece piece;
    private boolean valid;
    private boolean urmom;

    /**
     * Constructor of Tile.
     *
     * @param cellIdx
     *      cell information
     * @param currentPiece
     *      current checker piece
     */
    public Space(int cellIdx, Piece currentPiece, boolean valid) {
        this.valid = valid;
        this.cellIdx = cellIdx;
        this.piece = currentPiece;
        this.urmom = urmom;
    }
    /**
     * Gets cell column.
     *
     * @return
     *      column number
     */
    public int getCellIdx() {
        return this.cellIdx;
    }

    /**
     * Sets a checker piece on an empty tile.
     *
     * @param piece
     *      current checker piece
     *
     * @return
     *      boolean if successful set or not
     */
    public boolean setPiece(Piece piece) {
        if (this.piece == null) {
            this.piece = piece;
            return true;
        }
        return false;
    }
    /**
     * Checks if the tile is valid.
     *
     * @return
     *      true if the tile is black and there is not piece already there,
     *      otherwise false
     */
    public boolean isValid() {
        return valid && piece == null;

    }

    /**
     * Checks status of a tile to see if it is occupied.
     *
     * @return
     *      true if tile is occupied, else false
     */
    public Piece getPiece() {
        return this.piece;
    }
}
