package com.webcheckers.model;

import javax.swing.text.Position;

/**
 * Class containing the moves of a checker piece and its position.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 */
public class Move {
    /**
     * Global Variables
     */
    public enum moveType{Single,Capture, Invalid, None}

    private Position start;
    private Position end;
    public moveType Type;


    /**
     * Constructor of Move.
     * @param start starting position of the piece
     * @param end ending position of the piece
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
        Type = moveType.Invalid;
    }

    /**
     * Gets end position of a checker piece.
     * @return a position
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Gets the type of move used.
     * @return move type
     */
    public moveType getType() {
        return Type;
    }

    /**
     * Gets the start position of a checker piece.
     * @return a position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Sets the move type.
     * @param type move type
     */
    public void setType(moveType type) {
        Type = type;
    }

    /**
     * Sets the ending position of a checker piece.
     * @param end a position
     */
    public void setEnd(Position end) {
        this.end = end;
    }

    /**
     * Sets the starting position of a checker piece.
     * @param start a position
     */
    public void setStart(Position start) {
        this.start = start;
    }
}