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
        Game game = httpSession.attribute(GetGameRoute.GAMEID_ATTR);
        Game board = gameCenter.getGame(Integer.parseInt(GAMEID_ATTR));
        if(game == null)
            return gson.toJson(new Message("game doesn't exist", Message.Type.ERROR));

        Player player1 = httpSession.attribute(GetHomeRoute.CURRENT_USER);

        if(board.isResigned() && player1.isPlaying()) {
            return gson.toJson(Message.error(resignError));
        }
        board.resignGame(player1);
        board.endResignGame();
        if(board.isResigned()) {
            json = gson.toJson(Message.info("true"));
        } else {
            json = gson.toJson(Message.error(resignError));
        }
        return json;
    }
}
