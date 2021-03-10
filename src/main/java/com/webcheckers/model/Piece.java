package com.webcheckers.model;

public class Piece {
    public enum color{Red, White, None}
    public enum pieceType{Single, King}
    private color Color;
    private pieceType Type;


    public Piece(color Color) {
        Type = pieceType.Single;
        this.Color = Color;
    }


    public pieceType getType() {
        return Type;
    }

    public color getColor() {
        return Color;
    }

}
