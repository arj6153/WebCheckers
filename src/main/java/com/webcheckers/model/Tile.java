package com.webcheckers.model;

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

    public int getCellIdx() {
        return this.cellIdx;
    }

    public Status getTile() {
        return this.status;
    }

    public Status setPiece(Piece piece) {
        if (status == Status.EMPTY) {
            this.piece = piece;
            this.status = Status.FILLED;
            return this.status;
        }
        return this.status;
    }

    public boolean isValid() {
        return valid && piece == null;

    }

    public boolean isFilled() {
        return this.status == Status.FILLED;
    }

    public boolean isEmpty() {
        return this.status == Status.EMPTY;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
