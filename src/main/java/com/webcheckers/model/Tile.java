package com.webcheckers.model;

public class Tile {

    private int cellIdx;
    private Piece piece;
    private boolean valid;

    public Tile(int cellIdx, Piece currentPiece, boolean valid) {
        this.valid = valid;
        this.cellIdx = cellIdx;
        if (currentPiece == null) {
            this.piece = null;
        } else {
            this.piece = currentPiece;
        }
    }

    public int getCellIdx() {
        return this.cellIdx;
    }


    public boolean setPiece(Piece piece) {
        if (this.piece == null) {
            this.piece = piece;
            return true;
        }
        return false;
    }

    public boolean isValid() {
        return valid && piece == null;

    }


    public Piece getPiece() {
        return this.piece;
    }
}
