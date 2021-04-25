package com.webcheckers.model.AIEnhancement;

import com.webcheckers.appl.Game;
import com.webcheckers.model.*;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.webcheckers.appl.Game.Color.RED;
import static com.webcheckers.model.BoardView.DIM;

public class GameState {
    private boolean redTurn;
    private boolean gameOver;
    private String [][] board;
    public GameState(Game game) {
        gameOver = game.isGameOver();
        redTurn = false;
        makeBoardFromBoardView(game.redBoardView());
    }
    public GameState(String [][] board, boolean isRedTurn, boolean gameOver) {
        this.board = deepCopy(board);
        this.redTurn = isRedTurn;
        this.gameOver = gameOver;
    }



    public String [][] deepCopy(String [][] arr) {
        String [][] copy = new String[arr.length][];
        for(int i = 0; i < arr.length; i++)
            copy[i] = arr[i].clone();
        return copy;
    }
    public void makeBoardFromBoardView(BoardView boardView) {
        board = new String [DIM][DIM];
        for(int r = DIM-1; r>=0; r--) {
            for (int c = 0; c < DIM; c++) {
                Space space = boardView.getRow(r).getSpace(c);
                if(space.getPiece() == null) {
                    board[r][c] = ".";
                } else if (space.getPiece().getColor() == RED) {
                    board[r][c] = "R";
                } else {
                    board[r][c] = "W";
                }
            }

        }
    }

    public void setGameOver() {
        gameOver = true;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public String toString() {
        String str = "";
        for(int r = DIM-1; r >= 0; r--) {
            for(int c = 0; c < DIM; c++) {
                str += board[r][c] + " ";
            }
            str += "\n";
        }
        return str;
    }
}
