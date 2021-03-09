package com.webcheckers.ui;

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

    private static final Message TEST_MSG = Message.info("Test Sign in");

    private final TemplateEngine templateEngine;

    public GetSignInRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSignInRoute is initialized.");
    }
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSIgnInRoute is invoked");
        Map<String,Object> vm = new HashMap<>();
        vm.put("title", TEST_MSG);
        vm.put("message", "fda");
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
