package com.webcheckers.appl;

public class Game {
    private Player redPlayer;
    private Player whitePlayer;
    private Piece[][] board;

    public Game(Player red, Player white)
    {
        this.redPlayer = red;
        this.whitePlayer = white;
        this.board = newBoardSetup();
    }

    /**
     * Create a new standard board based on checkers rules.
     *
     * @return
     *    the new board, as an array of piece enums
     */
    private Piece[][] newBoardSetup()
    {
        Piece[][] newBoard = new Piece[8][8];

        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                // White side (top)
                if (row <= 2)
                {
                    if (row % 2 == 0) // From the top, rows 0 and 2
                    {
                        if (col % 2 == 0) // Even columns
                        {
                            newBoard[row][col] = Piece.None;
                        }
                        else // Odd columns
                        {
                            newBoard[row][col] = Piece.White;
                        }
                    }
                    else // Row 1
                    {
                        if (col % 2 == 0) // Even columns
                        {
                            newBoard[row][col] = Piece.White;
                        }
                        else // Odd columns
                        {
                            newBoard[row][col] = Piece.None;
                        }
                    }
                }
                // Red side (bottom)
                else if (row >= 5)
                {
                    if (row % 2 == 0) // From the top, rows 5 and 7
                    {
                        if (col % 2 == 0) // Even columns
                        {
                            newBoard[row][col] = Piece.None;
                        }
                        else // Odd columns
                        {
                            newBoard[row][col] = Piece.Red;
                        }
                    }
                    else // Row 6
                    {
                        if (col % 2 == 0) // Even columns
                        {
                            newBoard[row][col] = Piece.Red;
                        }
                        else // Odd columns
                        {
                            newBoard[row][col] = Piece.None;
                        }
                    }
                }
                else // Other rows not accounted for, e.g. 3 and 4
                {
                    newBoard[row][col] = Piece.None;
                }
            }
        }

        return newBoard;
    }

    /**
     * Returns the current game board.
     */
    public Piece[][] getBoard()
    {
        return this.board;
    }
}
