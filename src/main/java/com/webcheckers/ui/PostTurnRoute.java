package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

/**
 * UI controller for 00000000000
 *
 * @author:Truong Anh Tuan Hoang
 */
public class PostTurnRoute implements Route {

    /**
     * Global Variables
     */
    static final String GAMEID_ATTR = "gameID";
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private TemplateEngine templateEngine;
    private Gson gson;
    private final GameCenter gameCenter;

    /**
     * Constructor of PostTurnRoute
     * @param templateEngine
     * @param gameCenter the instance of the game center
     * @param gson
     */
    public PostTurnRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostTurnRoute has been invoked.");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game board = gameCenter.getGame(Integer.parseInt(GAMEID_ATTR));

        LOG.finer("The Red Players Pieces: " + board.getNumRedPieces());
        LOG.finer("The White Players Pieces: " + board.getNumWhitePieces());
        String json;
        if(board.isRedTurn(player)) {
            json = gson.toJson(Message.info("true"));
        } else {
            json = gson.toJson(Message.info("flase"));
        }
        return json;
    }
}
