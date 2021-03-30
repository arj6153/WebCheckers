package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
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
        String gameID = request.queryParams("gameID");
        Game game = gameCenter.getGame(Integer.parseInt(gameID));
        game.move(game.getActiveMove());
        if(game.isRedTurn()) {
            game.setPlayerTurn(game.getWhitePlayer());
        } else {
            game.setPlayerTurn(game.getRedPlayer());
        }
        return gson.toJson(new Message("valid", Message.Type.INFO));
    }
}
