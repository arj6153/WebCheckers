package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;


public class GetSignInRoute implements Route {
    //Attributes
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    static final String VIEW_NAME = "signin.ftl";
    static final Message SIGN_IN_MSG = Message.info("Please enter your name!");
    static final String DESCRIPTION = "Sign-In Form";

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public GetSignInRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("GetSignInRoute is initialized.");
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSIgnInRoute is invoked");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute("currentUser");
        if (player == null) {
            Map<String,Object> vm = new HashMap<>();
            vm.put("title", DESCRIPTION);
            vm.put("message", SIGN_IN_MSG);
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
