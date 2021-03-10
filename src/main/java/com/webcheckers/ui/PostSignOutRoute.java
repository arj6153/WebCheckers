package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Player;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSignOutRoute implements Route{

    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());

    private final GameCenter gameCenter;

    public <templateEngine> PostSignOutRoute(GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("PostSignOutRoute has been initialized.");
    }

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
