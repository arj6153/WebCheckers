package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static com.webcheckers.ui.GetHomeRoute.*;
import static spark.Spark.halt;

/**
 * The {@code Post /signin} route handler
 *
 * @author Alex Johannesson
 * @author Truong Anh Tuan Hoang
 */
public class PostSignInRoute implements Route {

    /**
     * Global Variables
     */
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final String USER_ID = "userID";
    private TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * creates the sign in route
     *
     * @param gameCenter
     *      the instance of the game center
     * @param templateEngine
     *      the HTML template rendering engine
     * @param <templateEngine>
     *      template engine
     */
    public <templateEngine> PostSignInRoute(GameCenter gameCenter, TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required.");
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required.");
        LOG.config("PostSignInRoute has been initialized.");
    }

    /**
     * Handles a POST /signin , player signing in
     *
     * @param request
     *      the HTTP request
     * @param response
     *      the HTTP response
     *
     * @return
     *      Player signing in, renders new home page view
     * @throws Exception // doesn't actually throw
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostSignInRoute has been invoked.");
        final Session httpSession = request.session();
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Map<String,Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, GetSignInRoute.DESCRIPTION);
        vm.put(MESSAGE_ATTR, GetSignInRoute.SIGN_IN_MSG);
        if (player == null) {
            String name = request.queryParams(USER_ID);
            Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
            ModelAndView mv;
            if(name.isBlank()) {
                mv = error(vm, "Username is empty. Try again.");
                return templateEngine.render(mv);
            } else if (name.charAt(0) == ' ') {
                mv = error(vm, "Username should not contain spaces. Try again.");
                return  templateEngine.render(mv);
            } else if (!p.matcher(name).find()) {
                mv = error(vm, "Username should contain alphanumeric values only. " +
                        "Spaces and special characters are not allowed. Try again.");
                return templateEngine.render(mv);
            } else if(gameCenter.getLobby().playerExists(name)) {
                mv = error(vm, "Username is taken. Try again.");
                return templateEngine.render(mv);
            }
            gameCenter.addPlayer(name);
            gameCenter.updatePopulation(1);
            vm.put(GetHomeRoute.CURRENT_USER, gameCenter.getPlayer(name));
            httpSession.attribute(GetHomeRoute.CURRENT_USER,gameCenter.getPlayer(name));
        }
        //return templateEngine.render(vm);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }

    /**
     * @param vm
     *      map of view model
     * @param message
     *      Sign in message
     *
     * @return
     *      rendered sign in page
     */
    private ModelAndView error(final Map<String, Object> vm, final String message) {
        vm.put(MESSAGE_ATTR, Message.error(message));
        vm.put(MESSAGE_TYPE_ATTR, ERROR_ATTR);
        return new ModelAndView(vm, GetSignInRoute.VIEW_NAME);
    }
}
