package com.webcheckers.model.AIEnhancement;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;

public class AIPlayer extends Player {
    private final GameCenter gameCenter;

    /**
     * Constructor for AIPlayer.
     *
     */
    public AIPlayer(GameCenter gameCenter) {
       super("ai#"+gameCenter.getAiNum());
       this.gameCenter = gameCenter;
       this.gameCenter.addAiNum();
    }
    /**
    public void makeMove() {
        Game game = gameCenter.getGame(this);
        if(game.getPlayerTurn().equals(this)) {
            ArrayList<Move> moves = game.getPossibleJumpMove();
            moves.addAll(game.getPossibleSimpleMove());
            if(!moves.isEmpty()) {
                Move move = moves.get(0);
                if(move.getType() == Move.MoveType.CAPTURE_MOVE) {
                    while (move != null) {
                        move = game.addNextJump(move);
                    }
                    Deque<Move> activeMoves = game.getActiveMove();
                    for (Move temp : activeMoves) {
                        temp.printMove();
                    }
                    while (game.getFrontMove() != null) {
                        move = game.pollFirstMove();
                        game.move(move, move.getType());
                    }
                } else {
                    game.move(move, move.getType());
                }
                game.clearActiveMove();
            }
            game.setPlayerTurn(gameCenter.getOpponent(this));
        }
    }
     **/
    public void makeMove() {
        Game game = gameCenter.getGame(this);
        if(game.getPlayerTurn().equals(this)) {
            if(game.canMove()) {
                GameState gameState = new GameState(game);
                MiniMax minimaxAlgo = new MiniMax();
                EvaluatedGameState eval = minimaxAlgo.minimax(null, gameState, true, 5);
                if (eval.getMove() != null) {
                    ArrayList<Move> moves;
                    if (eval.getMove().getType() == Move.MoveType.SINGLE_MOVE) {
                        game.move(eval.getMove(), eval.getMove().getType());
                    } else {
                        moves = gameState.getMaxJumpMove(eval.getMove().getStart());
                        for (Move m : moves) {
                            game.move(m, m.getType());
                        }
                    }
                } else {
                    ArrayList<Move> endMoves = game.getAllPossibleMove();
                    game.move(endMoves.get(0), endMoves.get(0).getType());
                }
            } else {
                game.setGameOver();
            }
            game.clearActiveMove();
            game.setPlayerTurn(gameCenter.getOpponent(this));
        }
    }
}
