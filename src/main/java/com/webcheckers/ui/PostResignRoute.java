package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
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
    static final String GAMEID_ATTR = "gameID";
    static final String RESIGNED_ATTR = "resigned";

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
     */
    public PostResignRoute(GameCenter gameCenter, Gson gson) {
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
        Player resigner = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game game = gameCenter.getGame(resigner);
        if(game == null) {
            return gson.toJson(new Message("game doesn't exist", Message.Type.ERROR));
        }
        Player player = gameCenter.getOpponent(resigner);

        if(game.isPlayerInGame(player) && game.isPlayerInGame(resigner)) {
            game.endResignGame();
            httpSession.attribute(RESIGNED_ATTR, true);
            json = gson.toJson(Message.info("true"));
        }
        else {
            httpSession.attribute(RESIGNED_ATTR, true);
        }
        if(game.isResigned() && player.isPlaying()) {
            return gson.toJson(Message.error(resignError));
        }
        game.resignGame(player);
        game.endResignGame();
        if(game.isResigned()) {
            json = gson.toJson(Message.info("true"));
        } else {
            json = gson.toJson(Message.error(resignError));
        }
        return json;
    }
}
