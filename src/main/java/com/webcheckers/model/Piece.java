package com.webcheckers.model;

public class Piece {
    public enum Color {Red, White}
    private boolean king;
    private Piece.Color color;


    public Piece(Piece.Color Color) {
        king = false;
        this.color = Color;
    }

    public boolean isKing()
    {
        return king;
    }

    public Piece.Color getColor() {
        return color;
    }

}
