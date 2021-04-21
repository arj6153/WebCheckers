package com.webcheckers.model.AIEnhancement;

import com.webcheckers.appl.Game;
import com.webcheckers.model.*;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.webcheckers.appl.Game.Color.RED;
import static com.webcheckers.model.BoardView.DIM;

public class GameState {
    private final Player redPlayer;
    private final Player whitePlayer;
    private Player playerTurn;
    private final Deque<Move> activeMove;
    private boolean gameOver;
    private BoardView boardView;
    public GameState(Game game) {
        redPlayer = new Player("min_player");
        whitePlayer = new Player("max_player");
        gameOver = game.isGameOver();
        activeMove = game.getActiveMove();
        playerTurn = whitePlayer;
        this.boardView = makeBoardFromBoardView(game.redBoardView());
    }
    public BoardView makeBoardFromBoardView(BoardView boardView) {
        List<Row> board = new ArrayList<>();
        for(int r = DIM-1; r>=0; r--) {
            Row row = new Row(boardView.getRow(r).getIndex());
            for (int c = 0; c < DIM; c++) {
                Space space = boardView.getRow(r).getSpace(c);
                row.add(space);
            }
            board.add(row);
        }

        return new BoardView(board, boardView.getRedPieces(), boardView.getWhitePieces());
    }
    public BoardView getBoardView() {
        return this.boardView;
    }
    public void simulateMove(Move move, Move.MoveType type){
        int startRow = move.getStart().getRow();
        int startCell = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCell = move.getEnd().getCell();
        Space space = boardView.getRow(startRow).getSpace(startCell);
        Piece piece = space.getPiece();
        if (isWhitePlayer(playerTurn) && boardView.getRow(endRow).getIndex() == 0) {
            piece.setKing();
        } else if (isRedPlayer(playerTurn) && boardView.getRow(endRow).getIndex()== DIM-1) {
            piece.setKing();
        }
        space.setPiece(null);
        if (type == Move.MoveType.SINGLE_MOVE) {
            boardView.getRow(endRow).getSpace(endCell).setPiece(piece);
        } else if(type == Move.MoveType.CAPTURE_MOVE) {
            boardView.getRow(endRow).getSpace(endCell).setPiece(piece);
            boardView.getRow((endRow+startRow)/2).getSpace((endCell+startCell)/2).setPiece(null);
            if(isRedPlayer(playerTurn)) {
                boardView.increaseOrDecreaseWhitePieces(-1);
            }
            else if(isWhitePlayer(playerTurn)) {
                boardView.increaseOrDecreaseRedPieces(-1);
            }
        }
        if (boardView.getWhitePieces() == 0 || boardView.getRedPieces() == 0) {
            setGameOver();
        }
    }

    public boolean isWhitePlayer(Player player) {
        return player.equals(this.whitePlayer);
    }

    public boolean isRedPlayer(Player player) {
        return player.equals(this.redPlayer);
    }

    public void setGameOver() {
        redPlayer.setPlaying(false);
        whitePlayer.setPlaying(false);
        gameOver = true;
    }
}
