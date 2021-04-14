package com.webcheckers.model;

/**
 * Class containing the moves of a checker piece and its position.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 */
public class Move {

    public enum MoveType {SINGLE_MOVE, CAPTURE_MOVE, INVALID_MOVE}

    private final Position start;
    private final Position end;
    public MoveType Type;

    /**
     * Constructor of Move.
     *
     * @param start
     *      The starting position of the piece
     * @param end
     *      The ending position of the piece
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
        Type = MoveType.INVALID_MOVE;
    }

    /**
     * @return
     *      The ending position of a piece
     */
    public Position getEnd() {
        return end;
    }

    /**
     * @return
     *      The move type
     */
    public MoveType getType() {
        return Type;
    }

    /**
     * @return
     *      The starting position of a piece
     */
    public Position getStart() {
        return start;
    }

    /**
     * Sets the move type.
     *
     * @param type
     *      The move type
     * @return
     */
    public MoveType setType(MoveType type) {
        Type = type;
        return type;
    }
    public void printMove() {
        System.out.println(this.getStart().getRow()+" "+this.getStart().getCell());
        System.out.println(this.getEnd().getRow()+" "+this.getEnd().getCell());
        System.out.println(this.getType());
        System.out.println();
    }
}