package com.webcheckers.model;

import com.webcheckers.appl.Lobby;

/**
 * Class for types of pieces in a checker game.
 *
 * @author Michael Merlino
 */
public class Piece {
    public enum Type {
        SINGLE, KING
    }


    private Lobby.Game.Color color;
    private Type type;

    /**
     * Constructor of Piece.
     *
     * @param color
     *      red or white
     */
    public Piece(Type type, Lobby.Game.Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Checks if a checker piece is a king piece.
     *
     * @return
     *      true if a checker piece is a king, else false
     */
    public boolean isKing()
    {
        return this.type == Type.KING;
    }

    /**
     * Gets the color of a checker piece.
     *
     * @return
     *      red or white
     */
    public Lobby.Game.Color getColor() {
        return this.color;
    }

    /**
     * Gets the Piece type.
     *
     * @return
     *      Piece Type
     */
    public Type getType() {
        return this.type;
    }
}
