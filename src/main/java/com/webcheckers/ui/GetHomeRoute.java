package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;
import com.webcheckers.util.Message;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author: Truong Anh Tuan Hoang
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
  static final String TITLE_ATTR = "title";
  static final String PLAYER_LIST_ATTR = "playerList";

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *      The HTML template rendering engine
   */
  public GetHomeRoute(final GameCenter gameCenter, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *      The HTTP request
   * @param response
   *      The HTTP response
   *
   * @return
   *      The rendered HTML for the Home page
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
      //System.out.println(player.getName() + " " +player.isPlaying());
      System.out.println("From home route " + player);
      if(player.isPlaying()) {
        int gameID = 0;
        boolean foundGame = false;
        for (Game game: gameCenter.getGameMap().values()) {
          if(game.isPlayerInGame(player) && !game.isGameOver()) {
            gameID = game.getID();
            foundGame = true;
            break;
          }
        }
        if (foundGame) {
          response.redirect(WebServer.GAME_URL + "?gameID=" + gameID);
        }
        halt();
        return null;
      } else if (!player.isPlaying() && gameCenter.getGame(player) != null && gameCenter.getGame(player).isGameOver()) {
        gameCenter.getLobby().addPlayer(player);
        Game game = gameCenter.getGame(player);
        if(gameCenter.getLobby().playerExists(game.getRedPlayer().getName())
                && gameCenter.getLobby().playerExists(game.getWhitePlayer().getName())) {
          gameCenter.getGameMap().remove(game.getID());
          response.redirect(WebServer.HOME_URL);
          halt();
          return null;
        }
      }
      if(message != null) {
        vm.put(MESSAGE_ATTR, message);
        httpSession.attribute(MESSAGE_ATTR, null);
      } else {
        vm.put(MESSAGE_ATTR, Message.info("Choose a Player"));
      }
      vm.put(CURRENT_USER, player);
      vm.put(PLAYER_LIST_ATTR, gameCenter.getLobby().getMap());
    } else {
      // display a user message in the Home page
      vm.put(LOBBY_COUNT, gameCenter.getLobby().getLobbySize());
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
