package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;

    public PostCheckTurnRoute(GameCenter gameCenter, Gson gson) {
        LOG.config("PostCheckTurnRoute is initialized.");
        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PoseCheckTurnRoute is invoked.");
        final Session httpSession = request.session();
        Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game game = gameCenter.getGame(player);
        String json;
        if (game.isGameOver()) {
            json = gson.toJson(Message.info("true"));
        }
        else {
            if(game.getPlayerTurn().equals(player)) {
                json = gson.toJson(Message.info("true"));
            } else {
                json = gson.toJson(Message.info("false"));
            }
        }
        return json;
    }
}
