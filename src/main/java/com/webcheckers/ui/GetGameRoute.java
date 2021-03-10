package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetGameRoute implements Route {
    //Attributes
    private final String VIEW_NAME = "game.ftl";
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private static final Message TITLE_MSG = Message.info("Checkers Game");

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public GetGameRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetGameRoute is initialized.");
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetGameRoute is invoked");
        Map<String,Object> vm = new HashMap<>();
        vm.put("title", TITLE_MSG);
        //vm.put("message", "fda");
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
