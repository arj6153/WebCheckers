package com.webcheckers.model.AIEnhancement;

import com.webcheckers.model.Move;

public class MiniMax {
    public EvaluatedGameState minimax(GameState gameState, boolean maxPlayer, int depth){
        if (depth == 0 || gameState.isGameOver()) {
            return new EvaluatedGameState(gameState.evaluate(), gameState);
        }
        if (maxPlayer) {
            double maxEvaluation = Double.MIN_VALUE;
            Move bestMove = null;
        }
    }
}
