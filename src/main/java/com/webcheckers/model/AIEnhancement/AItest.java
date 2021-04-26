package com.webcheckers.model.AIEnhancement;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;

import java.util.ArrayList;

public class AItest {
    public static void main(String [] args) {
        BoardView boardView = new BoardView();
        GameState gameState = new GameState(boardView);
        System.out.println(gameState);
        Move move = new Move(new Position(4,4), new Position(3,3));
        move.setType(Move.MoveType.CAPTURE_MOVE);
        gameState.move(move);
        System.out.println(gameState);

    }
}
