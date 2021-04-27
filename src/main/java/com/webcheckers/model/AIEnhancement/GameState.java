package com.webcheckers.model.AIEnhancement;

import com.webcheckers.appl.Game;
import com.webcheckers.model.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.webcheckers.appl.Game.Color.RED;
import static com.webcheckers.appl.Game.Color.WHITE;
import static com.webcheckers.model.BoardView.DIM;

public class GameState {
    private boolean redTurn;
    private boolean gameOver;
    private String [][] board;
    private int redPieces;
    private int redKingPieces = 0;
    private int whiteKingPieces = 0;
    private int whitePieces;
    private ArrayList<Move> jumped = new ArrayList<>();

    public GameState(Game game) {
        redPieces = game.getNumPieces(RED);
        whitePieces = game.getNumPieces(WHITE);
        gameOver = game.isGameOver();
        redTurn = false;
        makeBoardFromBoardView(game.redBoardView());
    }
    public GameState( BoardView boardView) {
        redPieces = boardView.getRedPieces();
        whitePieces = boardView.getWhitePieces();
        gameOver = false;
        redTurn = false;
        makeBoardFromBoardView(boardView);
    }
    public GameState(GameState gameState) {
        this.redKingPieces = gameState.redKingPieces;
        this.whiteKingPieces = gameState.whiteKingPieces;
        this.redPieces = gameState.redPieces;
        this.whitePieces = gameState.whitePieces;
        this.board = deepCopy(gameState.getBoard());
        this.redTurn = gameState.isRedTurn();
        this.gameOver = gameState.gameOver;
    }


    public double evaluate() {
        return whitePieces - redPieces + (whiteKingPieces*0.5 - redKingPieces*0.5);
    }
    public int getRedPieces() {
        return redPieces;
    }
    public int getWhitePieces() {
        return whitePieces;
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
                    if (space.getPiece().getType() == Piece.Type.KING){
                        board[r][c] = "RK";
                        redKingPieces++;
                    } else {
                        board[r][c] = "R";
                    }
                } else {
                    if (space.getPiece().getType() == Piece.Type.KING) {
                        board[r][c] = "WK";
                        whiteKingPieces++;
                    }
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

    public void move(Move move) {
        if(move.getType() == Move.MoveType.SINGLE_MOVE) {
            simulateMove(move);
        } else {
            ArrayList<Move> moves = getMaxJumpMove(move.getStart());
            jumped = moves;
            for (Move temp : moves) {
                simulateMove(temp);
            }
        }
    }
    public void simulateMove(Move move) {
        if (move.getType() == Move.MoveType.SINGLE_MOVE) {
            String piece = getPiece(move.getStart());
            setPiece(move.getStart(), ".");
            setPiece(move.getEnd(), piece);
        } else if (move.getType() == Move.MoveType.CAPTURE_MOVE) {
            String piece = getPiece(move.getStart());
            Position captured = new Position((move.getEnd().getRow()+move.getStart().getRow())/2,
                    (move.getEnd().getCell()+move.getStart().getCell())/2);
            setPiece(move.getStart(), ".");
            if (getPiece(captured).contains("R")) {
                redPieces--;
            } else if (getPiece(captured).contains("W")) {
                whitePieces--;
            }
            setPiece(captured, ".");
            if(redTurn && move.getEnd().getRow() == DIM-1) {
                redKingPieces++;
                setPiece(move.getEnd(), "RK");
            } else if (!redTurn && move.getEnd().getRow() == 0) {
                whiteKingPieces++;
                setPiece(move.getEnd(), "WK");
            } else {
                setPiece(move.getEnd(), piece);
            }
            if (redPieces == 0 || whitePieces == 0) {
                setGameOver();
            }

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



    public ArrayList<Move> getAllPossibleMove() {
        ArrayList<Move> arr = getPossibleJumpMove();
        if (arr.isEmpty()) {
            arr.addAll(getPossibleSimpleMove());
        }
        return arr;

    }
    public ArrayList<Move> getPossibleSimpleMove() {
        // iterate through the board
        ArrayList<Move> moves = new ArrayList<>();
        for (int r = 0; r < DIM; r++) {
            for (int c = 0; c < DIM; c++) {
                // get the starting position of the piece
                Position startPos = new Position(r,c);

                String piece = getPiece(startPos);
                // checks if the piece null and if it is the correct color
                if ((piece.equals(".")) || (!piece.contains( getPlayerColor()))) {
                    continue;
                }
                ArrayList<Move> availMoveSpots = new ArrayList<>();
                canMoveHelper(availMoveSpots, startPos);
                for (Move move : availMoveSpots) {
                    if (isNotInRange(move.getEnd())) {
                        continue;
                    }
                    if (simpleMoveCheck(move)) {
                        move.setType(Move.MoveType.SINGLE_MOVE);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }
    public ArrayList<Move> getPossibleJumpMoveFromPiece(Position startPos) {
        ArrayList<Move> moves = new ArrayList<>();
        String piece = getPiece(startPos);
        // checks if the piece null and if it is the correct color
        if ((piece.equals(".")) || (!piece.contains( getPlayerColor()))) {
            return moves;
        }
        ArrayList<Move> availMoveSpots = new ArrayList<>();
        canJumpHelper(availMoveSpots, startPos);
        for (Move move : availMoveSpots) {
            if (isNotInRange(move.getEnd())) {
                continue;
            }
            if (jumpCheck(move)) {
                move.setType(Move.MoveType.CAPTURE_MOVE);
                moves.add(move);
            }
        }
        return moves;

    }

    public ArrayList<Move> getMaxJumpMove(Position startPos) {
        ArrayList<Move> res = new ArrayList<>();
        ArrayList<Position> maxJumps = maxJump(startPos, this);
        for(int i = 0; i < maxJumps.size(); i += 2) {
            Move move = new Move(maxJumps.get(i), maxJumps.get(i+1));
            move.setType(Move.MoveType.CAPTURE_MOVE);
            res.add(move);
        }
        return res;

    }
    public ArrayList<Move> getPossibleJumpMove() {
        // iterate through the board
        ArrayList<Move> moves = new ArrayList<>();
        for (int r = 0; r < DIM; r++) {
            for (int c = 0; c < DIM; c++) {
                // get the starting position of the piece
                moves.addAll(getPossibleJumpMoveFromPiece(new Position(r,c)));
            }
        }
        return moves;
    }

    public ArrayList<Position> maxJump(Position startPos, GameState state) {
        ArrayList<Position> maxJumps = new ArrayList<>();
        for(Move move: state.getPossibleJumpMoveFromPiece(startPos)) {
           GameState tempState = new GameState(state);
           ArrayList<Position> jumps = new ArrayList<>();
           tempState.simulateMove(move);
           jumps.add(move.getStart());
           jumps.add(move.getEnd());
           jumps.addAll(maxJump(move.getEnd(),tempState));
           if (jumps.size() >= maxJumps.size()) {
                maxJumps = jumps;
           }
        }
        return maxJumps;
    }
    public boolean isNotInRange(Position position) {
        return ((position.getRow() >= DIM || position.getRow() < 0) ||
                (position.getCell() >= DIM || position.getCell() < 0));
    }
    private void canMoveHelper (ArrayList<Move> availMoveSpots, Position startPos) {
        availMoveSpots.add(new Move(startPos, new Position(startPos.getRow()+1, startPos.getCell()-1)));
        availMoveSpots.add(new Move(startPos, new Position(startPos.getRow()+1, startPos.getCell()+1)));
        availMoveSpots.add(new Move(startPos, new Position(startPos.getRow()-1, startPos.getCell()-1)));
        availMoveSpots.add(new Move(startPos, new Position(startPos.getRow()-1, startPos.getCell()+1)));
    }
    private void canJumpHelper(ArrayList<Move> availJumpSpots, Position startPos) {
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
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

                    return !getPiece(captured).equals(".") && (!getPiece(captured).contains(getPlayerColor()))
                            && endPiece.equals(".");
                } else {
                    return false;
                }
            }
        return false;
    }
    public void submitTurn() {
        redTurn = !redTurn;
    }
    private boolean isValidCapture (int endRow, int startRow, int endCol, int startCol, String captured, String endPiece) {
        if (redTurn && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
            return !captured.equals(".") && endPiece.equals(".") && captured.contains("W");
        } else if (!redTurn && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
            return !captured.equals(".") && endPiece.equals(".") && captured.contains("R");
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
    public String [][] getBoard() {
        return board;
    }
    public boolean isRedTurn() {
        return redTurn;
    }
    public HashMap<Move, GameState> getSuccessors() {
        ArrayList<Move> moves = this.getAllPossibleMove();
        HashMap<Move, GameState> states = new HashMap<>();
        for(Move move: moves) {
            GameState tempState = new GameState(this);
            tempState.move(move);
            tempState.submitTurn();
            states.put(move, tempState);
        }
        return states;
    }
}
