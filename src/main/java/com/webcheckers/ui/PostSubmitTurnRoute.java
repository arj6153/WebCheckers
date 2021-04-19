package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSubmitTurnRoute implements Route {
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    public PostSubmitTurnRoute(GameCenter gamecenter, TemplateEngine templateEngine, Gson gson) {
        this.gameCenter = gamecenter;
        this.templateEngine = templateEngine;
        this.gson = gson;
        LOG.config("PostValidateMove is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSubmitTurnRoute has been invoked.");
        final Session httpSession = request.session();
        Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game game = gameCenter.getGame(player);
        //game.move(game.getLatestMove(), game.getLatestMove().getType());
        if (game.getFrontMove().getType() == Move.MoveType.CAPTURE_MOVE) {
            if (game.canJump(game.getLatestMove())) {
                return gson.toJson(new Message("You must continue jumping.", Message.Type.ERROR));
            }
        }
        while(game.getFrontMove() != null) {
            Move move = game.pollFirstMove();
            game.move(move, move.getType());
        }
        if(game.isRedTurn()) {
            game.setPlayerTurn(game.getWhitePlayer());
        } else {
            game.setPlayerTurn(game.getRedPlayer());
        }
        System.out.println(game.redBoardView().getRedPieces() + " " + game.whiteBoardView().getWhitePieces());
        game.clearActiveMove();
        // if player has no available moves then game over
        if (!game.canMove() && game.getNumPieces(Game.Color.RED) != 0 && game.getNumPieces(Game.Color.WHITE) != 0) {
            game.setGameOver();
            game.setGameOverMessage("Player " + gameCenter.getOpponent(player).getName()
                    + " has lost due no available moves. Player "
                    + player.getName() + " has won!");
        }
        return gson.toJson(new Message("Valid move.", Message.Type.INFO));
    }
}
