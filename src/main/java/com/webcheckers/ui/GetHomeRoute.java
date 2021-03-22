package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author:Truong Anh Tuan Hoang
 */
public class GetHomeRoute implements Route {

  /**
   * Global Variables
   */
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String LOBBY_COUNT = "lobbyCount";
  static final String CURRENT_USER = "currentUser";
  static final String VIEW_NAME = "home.ftl";
  static final String DESCRIPTION = "Home";
  static final String MESSAGE_ATTR = "message";
  static final String MESSAGE_TYPE_ATTR = "messageType";
  static final String ERROR_ATTR = "error";
  static final String TITLE_ATTR = "title";

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

    Map<String, Object> vm = new HashMap<>();
    vm.put(TITLE_ATTR, DESCRIPTION);
    vm.put(MESSAGE_ATTR, WELCOME_MSG);
    final Message message = httpSession.attribute(MESSAGE_ATTR);
    if(player != null) {
      if(player.isPlaying()) {
        String gameID = "";
        for (Lobby.Game game: gameCenter.getGameMap().values()) {
          if(game.isPlayerInGame(player) && !game.isGameOver()) {
            game.setPlayerTurn(player);
            gameID = String.valueOf(game.getID());
            break;
          }
        }
        response.redirect(WebServer.GAME_URL + "?gameID="+ gameID);
        halt();
        return null;
      }
      if(message != null) {
        vm.put(MESSAGE_ATTR, message);
        httpSession.attribute(MESSAGE_ATTR, null);
      } else {
        vm.put(MESSAGE_ATTR, Message.info("Choose a Player"));
      }
      vm.put(CURRENT_USER, player);
      vm.put("playerList", gameCenter.getLobby().getMap());
    } else {
      // display a user message in the Home page

      vm.put(LOBBY_COUNT, gameCenter.getLobby().getLobbySize());
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
