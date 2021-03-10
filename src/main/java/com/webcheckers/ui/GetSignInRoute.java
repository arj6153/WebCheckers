package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetSignInRoute implements Route {
    //Attributes
    private final String VIEW_NAME = "signin.ftl";
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private static final Message SIGN_IN_MSG = Message.info("Please enter your name!");

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
        Map<String,Object> vm = new HashMap<>();
        vm.put("title", "Sign In Form");
        vm.put("message", SIGN_IN_MSG);
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
