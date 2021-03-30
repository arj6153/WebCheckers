
package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

import static com.webcheckers.appl.GameCenter.*;

/**
 *
 */
public class PostResignRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostResignRoute.class.getName());
    private GameCenter gameCenter;
    private Gson gson;
    private String json;


    public PostResignRoute(GameCenter gameCenter, Gson gson, Session httpSession) {
        LOG.config("PostResignRoute Initialized");
        this.gameCenter = gameCenter;
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session httpSession = request.session();
        Player player1 = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        return null;
    }
}
