package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The {@code Post /signout} route handler
 */
public class PostSignOutRoute implements Route{

    /**
     * Global variable
     */
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());
    private final GameCenter gameCenter;

    /**
     * Creates the signout route
     * @param gameCenter
     * @param <templateEngine>
     */
    public <templateEngine> PostSignOutRoute(GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("PostSignOutRoute has been initialized.");
    }

    /**
     * Handles a POST /signout , player signing out
     * Renders the signout page
     *
     * @param request the http request
     * @param response the http response
     * @return null
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSignInRoute has been invoked.");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute("currentUser");
        gameCenter.removePlayer(player.getName());
        httpSession.attribute("currentUser", null);
        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
