package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.logging.Logger;


/**
 * UI controller for the Post Resign Route
 *
 * @author Alex Johannesson
 */
public class PostResignRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostResignRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;
    private String json;
    private String resignError = "Unable to resign";


    /**
     * Creates the UI controller to handle The {@code Post /Resign Route} route handler
     *
     * @param gameCenter
     *      the gamecenter
     * @param gson
     *      the instance of the gson
     * @param httpSession
     *      The http session
     */
    public PostResignRoute(GameCenter gameCenter, Gson gson, Session httpSession) {
        this.gameCenter = gameCenter;
        this.gson = gson;
    }


    /**
     * Handles Resigning Ajax requests
     *
     * @param request
     *      the request
     * @param response
     *      the response
     * @return
     *      json containing message
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostResignRoute has been invoked");
        Session httpSession = request.session();
        Player player1 = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game game = gameCenter.getGame(request.queryParams(GetGameRoute.GAMEID_ATTR));
        if(game.isResigned() && player1.isPlaying()) {
            return gson.toJson(Message.error(resignError));
        }
        game.resignGame(player1);
        if(game.isResigned() && !(player1.isPlaying())) {
            json = gson.toJson(Message.info("true"));
        } else {
            json = gson.toJson(Message.error(resignError));
        }
        return json;
    }
}
