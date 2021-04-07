package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.logging.Logger;


public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostBackupMoveRoute.class.getName());
    static final String GAMEID_ATTR = "gameID";
    private final Gson gson;
    private final GameCenter gameCenter;

    public PostBackupMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = gameCenter;
        LOG.config("PostBackupMoveRoute initialized");
        this.gson = gson;
    }

    /**
     * Handles a POST /backup, a player backing up from their move.
     *
     * @param request
     *      The HTTP request
     * @param response
     *      The HTTP response
     *
     * @return
     *      The previous position of the checker piece
     *
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostBackupMoveRoute invoked");
        Session httpSession = request.session();
        Player player = httpSession.attribute(GetHomeRoute.CURRENT_USER);
        Message message;
        Game game = gameCenter.getGame(player);
        if (player.equals(game.getPlayerTurn())) {
            boolean flag = game.backupMove();
            if (flag) {
                message = new Message("Move has been undone.", Message.Type.INFO);
            } else {
                message = new Message("No move to be undone.", Message.Type.ERROR);
            }
        } else {
            message = new Message("Not your turn.", Message.Type.ERROR);
        }
        Gson gson = new GsonBuilder().create();
        return gson.toJson(message);
    }
}