package com.webcheckers.model;

import com.webcheckers.appl.Game;

/**
 * Class for types of pieces in a checker game.
 *
 * @author Michael Merlino
 * @author Alex Johannesson
 * @author Truong Anh Tuan Hoang
 * @author Bin Qiu
 */

public class Piece {

    public enum Type {
        SINGLE, KING
    }

    private final Game.Color color;
    private Type type;

    /**
     * Constructor of Piece.
     *
     * @param color
     *      Red or white
     */
    public Piece(Type type, Game.Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Checks if a checker piece is a king piece.
     *
     * @return
     *      True if a checker piece is a king, else false
     */
    public boolean isKing()
    {
        return this.type == Type.KING;
    }

    /**
     * Sets a checker piece to king type.
     */
    public void setKing() {
        this.type = Type.KING;
    }

    /**
     * Gets the color of a checker piece.
     *
     * @return
     *      Red or white
     */
    public Game.Color getColor() {
        return this.color;
    }

    /**
     * Gets the piece type.
     *
     * @return
     *      Piece Type
     */
    public Type getType() {
        return this.type;
    }
}