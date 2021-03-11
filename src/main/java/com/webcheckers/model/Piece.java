package com.webcheckers.model;

/**
 * Class for types of pieces in a checker game.
 *
 * @author Michael Merlino
 */
public class Piece {
    public enum Type {
        NORMAL, KING
    }

    private Game.color color;
    private Type type;

    /**
     * Constructor of Piece.
     * @param color red or white
     */
    public Piece(Type type, Game.color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Checks if a checker piece is a king piece.
     * @return true if a checker piece is a king, else false
     */
    public boolean isKing()
    {
        return this.type == Type.KING;
    }

    /**
     * Gets the color of a checker piece.
     * @return red or white
     */
    public Game.color getColor() {
        return this.color;
    }

    /**
     * Gets the Piece type.
     * @return Piece Type
     */
    public Type getType() {
        return this.type;
    }
}
