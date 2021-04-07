package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.ui.GetHomeRoute.*;
import static spark.Spark.halt;

/**
 * The UI controller to GET the game page
 *
 * @author: Truong Anh Tuan Hoang
 */
public class GetGameRoute implements Route {
<<<<<<< HEAD

=======
    
    //Attributes
>>>>>>> b28fcd0ffb9aacf257aae30edd799b84657e374e
    final String VIEW_NAME = "game.ftl";
    static final String PLAYER_ATTR = "player";
    static final String GAMEID_ATTR = "gameID";
    static final String RED_PLAYER_ATTR = "redPlayer";
    static final String WHITE_PLAYER_ATTR = "whitePlayer";
    static final String VIEWMODE_ATTR = "viewMode";
    static final String ACTIVE_COLOR_ATTR = "activeColor";
    static final String MODEOPTIONS_ATTR = "modeOptionsAsJSON";
    static final String DESCRIPTION = "Checkers Game";
    static final String BOARD_ATTR = "board";

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final Gson gson;

    public enum Mode {
        PLAY
    }

    /**
     * Constructor of GetGameRoute.
     *
     * @param gameCenter
     *      The instance of the game center
     * @param templateEngine
     *      The HTML template rendering engine
     */
    public GetGameRoute(GameCenter gameCenter, TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required.");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required.");
        this.gson = gson;
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Renders the game page.
     *
     * @param request
     *      HTTP request
     * @param response
     *      HTTP response
     *
     * @return
     *      The rendered HTML
     *
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetGameRoute is invoked.");
        final Session httpSession = request.session();
        Map<String,Object> vm = new HashMap<>();
        final Map<String, Object> modeOptions = new HashMap<>(2);
        vm.put(TITLE_ATTR, DESCRIPTION);
        final Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        if (player == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        } else {
           String gameID = request.queryParams(GAMEID_ATTR);
            vm.put(CURRENT_USER, player);
            if(gameID == null) {
               if(!player.isPlaying()) {
                   final Player opponent = gameCenter.getPlayer(request.queryParams(PLAYER_ATTR));
                   if(opponent.isPlaying()) {
                       httpSession.attribute(MESSAGE_ATTR, Message.error("Player is in game. Choose a different player."));
                       response.redirect(WebServer.HOME_URL);
                   } else {
                       gameID = String.valueOf(gameCenter.addGame(player, opponent));
                       response.redirect(WebServer.GAME_URL + "?gameID=" + gameID);
                   }
                   return null;
               }
           } else {
               if(gameID.equals("")) {
                   httpSession.attribute(MESSAGE_ATTR, Message.error("Not in a game."));
                   player.setPlaying(false);
                   response.redirect(WebServer.HOME_URL);
                   halt();
                   return null;
               }
               vm.put(VIEWMODE_ATTR, Mode.PLAY);
               Game game = gameCenter.getGame(Integer.parseInt(gameID));
               vm.put(RED_PLAYER_ATTR, game.getRedPlayer());
               vm.put(WHITE_PLAYER_ATTR, game.getWhitePlayer());
               BoardView board = game.redBoardView();
               if (game.isGameOver()) {
                    modeOptions.put("isGameOver", true);
<<<<<<< HEAD
                    modeOptions.put("gameOverMessage", Message.info("Game Over"));
                   vm.put(MODEOPTIONS_ATTR, gson.toJson(modeOptions));
=======
                    modeOptions.put("gameOverMessage", Message.info("game is over"));
                    vm.put(MODEOPTIONS_ATTR, gson.toJson(modeOptions));
>>>>>>> b28fcd0ffb9aacf257aae30edd799b84657e374e
               }
               if(game.isWhitePlayer(player)) {
                  board = game.whiteBoardView();
               } else if (game.isRedPlayer(player)) {
                   board = game.redBoardView();
               }
               vm.put(BOARD_ATTR, board);
               vm.put(ACTIVE_COLOR_ATTR, game.getPlayerColor());
           }
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}