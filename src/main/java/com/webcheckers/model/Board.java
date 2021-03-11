package com.webcheckers.model;

public class Board {
    private Piece[][] board;
    private int whitePlayerPieces = 12;
    private int redPlayerPieces = 12;


    public Board() {
        this.board = new Piece[8][8];
        this.initializeBoard();
    }

    public int getWhitePlayerPieces() {
        return whitePlayerPieces;
    }

    public int getRedPlayerPieces() {
        return redPlayerPieces;
    }

    /**
     * Checks if a tile is occupied by a checker piece.
     * @param x  the X coordinate of the tile
     * @param y the Y coordinate of the tile
     * @return true if there is no checker on the tile, false if there is one
     */
    private boolean isTileEmpty(int x, int y)
    {
        return board[x][y] == null;
    }

    /**
     * Given the x,y coordinates of a piece and a target location, determines if the piece can be dropped at the target.
     * @param pieceX the X location of the selected piece
     * @param pieceY the Y location of the selected piece
     * @param targetX the X location of the target tile
     * @param targetY the Y location of the target tile
     * @return true if the piece can be dropped at the target, false is not
     */
    public boolean isDroppable(int pieceX, int pieceY, int targetX, int targetY)
    {
        // Is the target tile white or occupied? If so, not droppable
        // Is the tile where the selected piece should be actually occupied by a piece? If not, not droppable
        return !isWhiteTile(targetX, targetY) && isTileEmpty(targetX, targetY) && !isTileEmpty(pieceX, pieceY);
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
     * Checks if move is within boundary of the board.
     * @param row the row coordinate
     * @param col the column coordinate
     * @return true if move is within board boundary, else false
     */
    public boolean isValid(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
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



