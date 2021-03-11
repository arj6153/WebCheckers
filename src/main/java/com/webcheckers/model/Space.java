package com.webcheckers.model;

public class Space {

    private int cellIdx;
    private Piece piece;
    private Tile tile;

    public enum Tile {
        EMPTY,
        FILLED,
        INVALID
    }

    public Space(int cellIdx, Piece currentPiece) {
        this.cellIdx = cellIdx;
        if (currentPiece == null) {
            this.piece = null;
            this.tile = Tile.EMPTY;
        } else {
            this.piece = currentPiece;
            this.tile = Tile.FILLED;
        }
    }

    public int getCellIdx() {
        return this.cellIdx;
    }

    public Tile getTile() {
        return this.tile;
    }

    public Tile setPiece(Piece piece) {
        if (tile == Tile.EMPTY) {
            this.piece = piece;
            this.tile = Tile.FILLED;
            return this.tile;
        }
        return this.tile;
    }

    public boolean isValid() {
        return !(this.tile == Tile.INVALID ||
                this.tile == Tile. FILLED);
    }

    public boolean isFilled() {
        return this.tile == Tile.FILLED;
    }

    public boolean isEmpty() {
        return this.tile == Tile.EMPTY;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
