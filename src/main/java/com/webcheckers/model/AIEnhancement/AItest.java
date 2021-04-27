package com.webcheckers.model.AIEnhancement;

import com.webcheckers.appl.Game;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class AItest {
    public static void main(String [] args) {
        BoardView boardView = new BoardView();
        GameState gameState = new GameState(boardView);
        MiniMax miniMaxAlgo = new MiniMax();
        EvaluatedGameState eval = miniMaxAlgo.minimax(null,gameState,true,2);
        System.out.println(eval.getMove().getType());
       // ArrayList<Move> jumped = gameState.getMaxJumpMove(eval.getMove().getStart());
        //for (Move move: jumped) {
        //    move.printMove();
        //}
    }
}
