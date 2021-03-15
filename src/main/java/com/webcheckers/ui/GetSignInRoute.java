package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * UI controller for the signin page.
 *
 * @author Alex Johannesson
 * @author Truong Anh Tuan Hoang
 */
public class GetSignInRoute implements Route {

    /**
     * Global Variables
     */
    //Attributes
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    static final String VIEW_NAME = "signin.ftl";
    static final Message SIGN_IN_MSG = Message.info("Please enter your name!");
    static final String DESCRIPTION = "Sign-In Form";

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Creates the UI controller to handle The {@code Get /signin} route handler
     *
     * @param gameCenter
     *      the instance of the game center
     * @param templateEngine
     *      the HTML template rendering engine
     */
    public GetSignInRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
        LOG.config("GetSignInRoute is initialized.");
    }

    /**
     * Renders the sign in page
     *
     * @param request
     *      the HTTP request
     * @param response
     *      the HTTP response
     *
     * @return
     *      the rendered sing in page
     * @throws Exception // doesn't actually throw
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSIgnInRoute is invoked");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        if (player == null) {
            Map<String,Object> vm = new HashMap<>();
            vm.put(GetHomeRoute.TITLE_ATTR, DESCRIPTION);
            vm.put(GetHomeRoute.MESSAGE_ATTR, SIGN_IN_MSG);

            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
