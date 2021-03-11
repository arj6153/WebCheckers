package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {

  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String LOBBY_COUNT = "lobbyCount";
  static final String CURRENT_USER = "currentUser";
  static final String VIEW_NAME = "home.ftl";
  static final String DESCRIPTION = "Home";
  static final String MESSAGE_ATTR = "message";
  static final String MESSAGE_TYPE_ATTR = "messageType";
  static final String ERROR_ATTR = "error";


  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(GameCenter gameCenter, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    final Session httpSession = request.session();
    final Player player = httpSession.attribute(CURRENT_USER);

    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", DESCRIPTION);
    if(player != null) {
      vm.put("message", Message.info(String.format("Hello, %s", player.getName())));
      vm.put(CURRENT_USER, player);
      vm.put("playerList", gameCenter.getLobby().getMap());
    } else {
      // display a user message in the Home page
      vm.put("message", WELCOME_MSG);
      vm.put(LOBBY_COUNT, gameCenter.getLobby().getLobbySize());
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
