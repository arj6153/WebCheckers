package com.webcheckers.model.AIEnhancement;

public class EvaluatedGameState {
    private final double evaluation;
    private final GameState gameState;
    public EvaluatedGameState(double evaluation, GameState gameState) {
        this.evaluation = evaluation;
        this.gameState = new GameState(gameState);
    }

    public double getEvaluation() {
        return evaluation;
    }

    public GameState getGameState() {
        return gameState;
    }
}
