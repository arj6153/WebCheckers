package com.webcheckers.model;

import javax.swing.text.Position;

public class Move {
    public enum moveType{Single,Capture, Invalid, None}

    private Position start;
    private Position end;
    public moveType Type;


    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
        Type = moveType.Invalid;
    }

    public Position getEnd() {
        return end;
    }

    public moveType getType() {
        return Type;
    }

    public Position getStart() {
        return start;
    }

    public void setType(moveType type) {
        Type = type;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public void setStart(Position start) {
        this.start = start;
    }

}
