package com.webcheckers.model;

public class GameBoard {
    private Player redPlayer;
    private Player whitePlayer;
    private Piece[][] board;
    private int whitePlayerPieces = 12;
    private int redPlayerPieces = 12;


    public GameBoard(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.board = new Piece[8][8];


    }

    public int getWhitePlayerPieces() {
        return whitePlayerPieces;
    }

    public int getRedPlayerPieces() {
        return redPlayerPieces;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Create a new standard board based on checkers rules.
     * @return a new board, as an array of piece enums
     */
    public Piece[][] initializeBoard() {
        Piece[][] newBoard = new Piece[8][8];
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                // White side (top)
                if (row <= 2 && isWhiteTile(row, col))
                {
                    board[row][col] = new Piece(Piece.Color.White);
                }
                // Red side (bottom)
                else if (row >= 5 && isWhiteTile(row, col))
                {
                    board[row][col] = new Piece(Piece.Color.Red);
                }
                else // Center field
                {
                    board[row][col] = null;
                }
            }
        }

        return newBoard;
    }



    /**
     * Returns whether or not a tile on the checker board is white.
     * @param x the X coordinate of the tile
     * @param y the Y coordinate of the tile
     * @return true if the tile is white, false if the tile is black
     */
    private boolean isWhiteTile(int x, int y)
    {
        return (x + y) % 2 == 0;
    }
}



