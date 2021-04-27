package com.webcheckers.model.AIEnhancement;

import com.webcheckers.model.Move;

public class EvaluatedGameState {
    private final double evaluation;
    private final Move move;
    public EvaluatedGameState(double evaluation, Move move) {
        this.evaluation = evaluation;
        this.move = move;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public Move getMove() {
        return move;
    }
}