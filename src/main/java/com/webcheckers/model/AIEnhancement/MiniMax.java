package com.webcheckers.model.AIEnhancement;
import com.webcheckers.appl.Game;
import com.webcheckers.model.Move;

import java.util.ArrayList;
import java.util.HashMap;

public class MiniMax {
    public EvaluatedGameState minimax(Move move, GameState gameState, boolean maxPlayer, int depth){
        if (depth == 0 || gameState.isGameOver()) {
            return new EvaluatedGameState(gameState.evaluate(), move);
        }
        if (maxPlayer) {
            double maxEvaluation = Double.MIN_VALUE;
            Move bestMove = move;
            HashMap<Move, GameState> successors = gameState.getSuccessors();
            for(Move tempMove: successors.keySet()) {
                GameState temp = successors.get(tempMove);
                EvaluatedGameState evaluation = minimax(tempMove,temp, false, depth-1);
                if (evaluation.getEvaluation() >= maxEvaluation) {
                    maxEvaluation = evaluation.getEvaluation();
                    bestMove = tempMove;
                }
            }
            return new EvaluatedGameState(maxEvaluation, bestMove);
        } else {
            double minEvaluation = Double.MAX_VALUE;
            Move bestMove = null;
            HashMap<Move, GameState> successors = gameState.getSuccessors();
            for(Move tempMove:successors.keySet()) {
                GameState temp = successors.get(tempMove);
                EvaluatedGameState evaluation = minimax(tempMove, temp, true, depth-1);
                if (evaluation.getEvaluation() <= minEvaluation) {
                    minEvaluation = evaluation.getEvaluation();
                    bestMove = tempMove;
                }
            }
            return new EvaluatedGameState(minEvaluation, bestMove);
        }
    }
}
