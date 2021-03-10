package com.webcheckers.model;

/**
 * Game logic of Webcheckers.
 *
 * @author Michael Merlino
 */
public class Game {
    private Player redPlayer;
    private Player whitePlayer;
    private Piece[][] board;
    private int ID;
    private int whitePlayerPieces = 12;
    private int redPlayerPieces = 12;
    private Player playerTurn;

    /**
     * Constructor of Game.
     * @param red player color
     * @param white opponent color
     */
    public Game(Player red, Player white)
    {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = newBoardSetup();
        this.ID = redPlayer.getName().hashCode() * 31 + whitePlayer.getName().hashCode() * 67;
    }

    /**
     * Get red player, this is the user.
     * @return red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Get white player, this is the opponent.
     * @return white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Checks if player is white player, opponent.
     * @param player white player
     * @return true if player is white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(getWhitePlayer());
    }

    /**
     * Gets pieces for the red player.
     * @return red pieces
     */
    public int getRedPlayerPieces() {
        return redPlayerPieces;
    }

    /**
     * Gets pieces for the white player.
     * @return white pieces
     */
    public int getWhitePlayerPieces() {
        return whitePlayerPieces;
    }

    /**
     * Checks if player is red player, user.
     * @param player red player
     * @return true if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(getRedPlayer());
    }


    /**
     * Create a new standard board based on checkers rules.
     * @return a new board, as an array of piece enums
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
                    board[row][col] = new Piece(Piece.Color.White);
                }
                // Red side (bottom)
                else if (row >= 5 && isWhiteTile(row, col))
                {
                    board[row][col] = new Piece(Piece.Color.Red);
                }
                else // Center field
                {
                    newBoard[row][col] = null;
                }
            }
        }

        return newBoard;
    }

    /**
     * Gets the game session's ID.
     * @return game ID
     */
    public int getID()
    {
        return this.ID;
    }

    /**
     * Get the board of the current instance
     * @return current board
     */
    public Piece[][] getBoard()
    {
        return this.board;
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
        return isWhiteTile(targetX, targetY) && isTileEmpty(targetX, targetY) || !isTileEmpty(pieceX, pieceY);
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
