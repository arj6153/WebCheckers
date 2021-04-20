package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The {@code Post /signout} route handler.
 *
 * @author Bin Qiu
 */
public class PostSignOutRoute implements Route{

    /**
     * Global variable
     */
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());
    private final GameCenter gameCenter;

    /**
     * Creates the sign out route
     *
     * @param gameCenter
     *      the instance of the game center
     * @param <templateEngine>
     *      the HTML template rendering engine
     */
    public <templateEngine> PostSignOutRoute(GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("PostSignOutRoute has been initialized.");
    }

    /**
     * Handles a POST /signout , player signing out
     * Renders the sign out page
     *
     * @param request
     *      the HTTP request
     * @param response
     *      the HTTP response
     *
     * @return
     *      null
     * @throws Exception // doesn't actually throw
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSignInRoute has been invoked.");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Game game = gameCenter.getGame(player);
        if(game != null) {
            game.setGameOverMessage(player.getName() + " disconnected. You won.");
            game.setGameOver();
        }
        gameCenter.removePlayer(player.getName());
        gameCenter.updatePopulation(-1);
        httpSession.attribute(GetHomeRoute.CURRENT_USER, null);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
