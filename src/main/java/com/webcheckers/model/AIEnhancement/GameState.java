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

    public String getPiece(Position position) {
        return board[position.getRow()][position.getCell()];
    }

    public void setPiece(Position position, String piece) {
        board[position.getRow()][position.getCell()] = piece;
    }

    public void move (Move move) {
        if (move.getType() == Move.MoveType.SINGLE_MOVE) {
            String piece = getPiece(move.getStart());
            setPiece(move.getStart(), ".");
            setPiece(move.getEnd(), piece);
        } else if (move.getType() == Move.MoveType.CAPTURE_MOVE) {
            String piece = getPiece(move.getStart());
            Position captured = new Position((move.getEnd().getRow()+move.getStart().getRow())/2,
                    (move.getEnd().getCell()+move.getStart().getCell())/2);
            String capPiece = getPiece(captured);
            setPiece(move.getStart(), ".");
            setPiece(captured, ".");
            setPiece(move.getEnd(), piece);
        }
    }

    public boolean simpleMoveCheck (Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        if (getPiece(move.getEnd()).equals(".") && !getPiece(move.getStart()).equals(".")
                && getPiece(move.getStart()).length() == 1) {
            if (redTurn && (endRow == startRow + 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1))) {
                return true;
            } else return !redTurn && (endRow == startRow - 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        } else if (getPiece(move.getEnd()).equals(".") && !getPiece(move.getStart()).equals(".")
                && getPiece(move.getStart()).length() == 2) {
            return ((endRow == startRow + 1) || (endRow == startRow - 1)) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        }
        return false;
    }

    public String getPlayerColor() {
        if (redTurn) {
            return "R";
        } return "W";
    }

    public boolean jumpCheck(Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        Position captured = new Position((move.getEnd().getRow()+move.getStart().getRow())/2,
                (move.getEnd().getCell()+move.getStart().getCell())/2);
        String startPiece = getPiece(move.getStart());
        String endPiece = getPiece(move.getEnd());
        boolean b = ((endRow == startRow + 2) || (endRow == startRow - 2))
                && ((endCol == startCol + 2) || (endCol == startCol - 2));
            if (!startPiece.equals(".") && startPiece.length() == 1) {
                return isValidCapture(endRow, startRow, endCol, startCol, getPiece(captured), endPiece);
            } else if (!startPiece.equals(".") && startPiece.length() == 2) {
                if (b) {
                    return !getPiece(captured).equals(".") && (getPiece(captured).contains(getPlayerColor()))
                            && !endPiece.equals(".");
                } else {
                    return false;
                }
            }

//        if (activeMove.peekFirst() != null && activeMove.peekFirst().getType() == Move.MoveType.CAPTURE_MOVE) {
//            Space originSpace = board.getRow(activeMove.peekFirst().getStart().getRow()).getSpace(activeMove.peekFirst().getStart().getCell());
//            // checks original space is not null and it's a single checker piece
//            if (originSpace.getPiece() != null && originSpace.getPiece().getType() == Piece.Type.SINGLE) {
//                return isValidCaptureSpace(endRow, startRow, endCol, startCol, capture, endSpace);
//            } else if (originSpace.getPiece() != null && originSpace.getPiece().getType() == Piece.Type.KING) {
//                Move previousMove = activeMove.peekLast();
//                assert previousMove != null;
//                if (previousMove.getStart().equal(move.getEnd())) {
//                    return false;
//                }
//                if (b) {
//                    return capture.getPiece() != null && capture.getPiece().getColor() != getPlayerColor()
//                            && endSpace.getPiece() == null;
//                } else {
//                    return false;
//                }
//            }
//        }
        return false;
    }

    private boolean isValidCapture (int endRow, int startRow, int endCol, int startCol, String captured, String endPiece) {
        if (redTurn && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
            return !captured.equals(".") && !endPiece.equals(".") && captured.contains("W");
        } else if (!redTurn && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
            return !captured.equals(".") && !endPiece.equals(".") && captured.contains("R");
        } else {
            return false;
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
