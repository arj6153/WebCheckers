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

public class PostValidateMove implements Route {


    //Attributes
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());


    public PostValidateMove(GameCenter gameCenter, TemplateEngine templateEngine, Gson gson) {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
        this.gson = gson;
        LOG.config("PostValidateMove is initialized.");

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostTurnRoute has been invoked.");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        String param = request.queryParams("actionData");
        Game game = gameCenter.getGame(player);
        Move move = gson.fromJson(param, Move.class);
        System.out.println("START "+ move.getStart().getRow() + " " + move.getStart().getCell());
        System.out.println("END "+ move.getEnd().getRow() + " " + move.getEnd().getCell());

        Message message = game.isValidMove(move);
        return new Gson().toJson(message);
    }
}
