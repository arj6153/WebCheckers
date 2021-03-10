package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;


public class PostSignInRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    //CONSTANT
    private final String USER_ID = "userID";
    private final String DESCRIPTION = "Sign in Form";

    // Attributes
    private TemplateEngine templateEngine = null;
    private final GameCenter gameCenter;


    public <templateEngine> PostSignInRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("PostSignInRoute has been initialized.");
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSignInRoute has been invoked.");
        final Session httpSession = request.session();

        Map<String,Object> vm = new HashMap<>();
        vm.put("title", DESCRIPTION);
        String name = request.queryParams(USER_ID);

        //return templateEngine.render(vm);
        response.redirect(WebServer.SIGN_IN_URL);
        halt();
        return null;
    }
}
