package com.webcheckers.model;

/**
 * Class for types of pieces in a checker game.
 */
public class Piece {
    public enum Color {Red, White}
    private boolean king;
    private Piece.Color color;

    /**
     * Constructor of Piece.
     * @param Color red or white
     */
    public Piece(Piece.Color Color) {
        king = false;
        this.color = Color;
    }

    /**
     * Checks if a checker piece is a king piece.
     * @return true if a checker piece is a king, else false
     */
    public boolean isKing()
    {
        return king;
    }

    /**
     * Gets the color of a checker piece.
     * @return red or white
     */
    public Piece.Color getColor() {
        return color;
    }

}
