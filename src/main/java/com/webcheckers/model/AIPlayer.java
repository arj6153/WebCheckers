package com.webcheckers.model;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;

import java.util.ArrayList;

public class AIPlayer extends Player{
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
    public void makeMove() {
        Game game = gameCenter.getGame(this);
        if(game.getPlayerTurn().equals(this)) {
            ArrayList<Move> moves = game.getPossibleJumpMove();
            moves.addAll(game.getPossibleSimpleMove());
            if(!moves.isEmpty()) {
                game.move(moves.get(0), moves.get(0).getType());
            }
            game.setPlayerTurn(gameCenter.getOpponent(this));
        }
    }
}
