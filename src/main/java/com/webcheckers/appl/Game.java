package com.webcheckers.appl;

import java.util.*;


public class Game {
    private Player redPlayer;
    private Player whitePlayer;
    private Piece[][] board;
    private int ID;
    private int whitePlayerPieces = 12;
    private int redPlayerPieces = 12;
    private Player playerTurn;


    public Game(Player red, Player white)
    {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = newBoardSetup();
        this.ID = redPlayer.getName().hashCode() * 31 + whitePlayer.getName().hashCode() * 67;
    }


    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public boolean isWhitePlayer(Player player) {
        return player.equals(getWhitePlayer());
    }

    public int getRedPlayerPieces() {
        return redPlayerPieces;
    }

    public int getWhitePlayerPieces() {
        return whitePlayerPieces;
    }

    public boolean isRedPlayer(Player player) {
        return player.equals(getRedPlayer());
    }


    /**
     * Create a new standard board based on checkers rules.
     *
     * @return
     *    The new board, as an array of piece enums
     */
    private Piece[][] newBoardSetup()
    {
        Piece[][] newBoard = new Piece[8][8];

        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                // White side (top)
                if (row <= 2 && isWhiteTile(row, col))
                {
                    board[row][col] = Piece.White;
                }
                // Red side (bottom)
                else if (row >= 5 && isWhiteTile(row, col))
                {
                    board[row][col] = Piece.Red;
                }
                else // Center field
                {
                    newBoard[row][col] = Piece.None;
                }
            }
        }

        return newBoard;
    }

    public int getID()
    {
        return this.ID;
    }

    public Piece[][] getBoard()
    {
        return this.board;
    }

    /**
     * Checks if a tile is occupied by a checker
     * @param x
     *      The X coordinate of the tile
     * @param y
     *      The Y coordinate of the tile
     * @return
     *      True if there is no checker on the tile, false if there is one
     */
    private boolean isTileEmpty(int x, int y)
    {
        return board[x][y] == Piece.None;
    }

    /**
     * Given the x,y coordinates of a piece and a target location, determines if the piece can be dropped at the target.
     * @param pieceX
     *      The X location of the selected piece
     * @param pieceY
     *      The Y location of the selected piece
     * @param targetX
     *      The X location of the target tile
     * @param targetY
     *      The Y location of the target tile
     * @return
     *      True if the piece can be dropped at the target, false is not
     */
    public boolean isDroppable(int pieceX, int pieceY, int targetX, int targetY)
    {
        // Is the target tile white or occupied? If so, not droppable
        // Is the tile where the selected piece should be actually occupied by a piece? If not, not droppable
        return isWhiteTile(targetX, targetY) && isTileEmpty(targetX, targetY) || !isTileEmpty(pieceX, pieceY);
    }


    public boolean isValid(int row, int col) {
        if(row <0 || row > 7 || col<0 || col>7) {
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Returns whether or not a tile on the checker board is white
     * @param x
     *      The X coordinate of the tile
     * @param y
     *      The Y coordinate of the tile
     * @return
     *      True if the tile is white, false if the tile is black
     */
    private boolean isWhiteTile(int x, int y)
    {
        return (x + y) % 2 == 0;
    }
}
