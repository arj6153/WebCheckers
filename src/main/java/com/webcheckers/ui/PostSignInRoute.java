package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private TemplateEngine templateEngine = null;
    private final GameCenter gameCenter;
    private final String USERNAME = "username";


    public <templateEngine> PostSignInRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("PostSignInRoute has been initialized.");
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        LOG.finer("PostSignInRoute has been invoked.");
        Map<String,Object> vm = new HashMap<>();
        vm.put("title", "Sign In");









        return templateEngine.render(vm);
    }
}
