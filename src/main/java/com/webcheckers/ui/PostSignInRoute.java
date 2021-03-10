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

import static spark.Spark.halt;


public class PostSignInRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    //CONSTANT
    private final String USER_ID = "userID";
    static final String MESSAGE_ATTR = "message";
    static final String MESSAGE_TYPE_ATTR = "messageType";
    private final String ERROR_TYPE = "error";

    // Attributes
    private TemplateEngine templateEngine;
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
        final Player player = httpSession.attribute("currentUser");
        Map<String,Object> vm = new HashMap<>();
        vm.put("title", GetSignInRoute.DESCRIPTION);
        vm.put("message", GetSignInRoute.SIGN_IN_MSG);
        if (player == null) {
            String name = request.queryParams(USER_ID);
            Pattern p = Pattern.compile("[a-zA-Z0-9 ]");
            ModelAndView mv;
            if(name.isBlank()) {
                mv = error(vm, "Username is empty. Try again");
                return templateEngine.render(mv);
            } else if (name.charAt(0) == ' ' || name.charAt(name.length()-1) == ' ') {
                mv = error(vm, "Username should not contains spaces in front or at the end of name. Try again");
                return  templateEngine.render(mv);
            }
            else if (!p.matcher(name).find()) {
                mv = error(vm, "Username should contains " +
                        "alphanumeric value and optional spaces only. Try again");
                return templateEngine.render(mv);
            } else if(gameCenter.getLobby().playerExists(name)) {
                mv = error(vm, "Username is taken Try again");
                return templateEngine.render(mv);
            }
            gameCenter.addPlayer(name);
            vm.put("currentUser", gameCenter.getPlayer(name));
            httpSession.attribute("currentUser",gameCenter.getPlayer(name));
        }
        //return templateEngine.render(vm);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }

    private ModelAndView error(final Map<String, Object> vm, final String message) {
        vm.put(MESSAGE_ATTR, Message.info(message));
        vm.put(MESSAGE_TYPE_ATTR, ERROR_TYPE);
        return new ModelAndView(vm, GetSignInRoute.VIEW_NAME);
    }
}
