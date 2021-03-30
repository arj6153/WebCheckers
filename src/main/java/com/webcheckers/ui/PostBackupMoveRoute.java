package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import org.eclipse.jetty.http.MimeTypes;
import spark.*;

import java.util.logging.Logger;


public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostBackupMoveRoute.class.getName());
    static final String GAMEID_ATTR = "gameID";
    private Gson gson;
    private final GameCenter gameCenter;

    public PostBackupMoveRoute(GameCenter gameCenter, Gson gson) {
        this.gameCenter = gameCenter;
        LOG.config("PostBackupMoveRoute initialized");
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostBackupMoveRoute invoked");
        Session httpSession = request.session().attribute("Player");
        String json;
        Message message;
        Game board = gameCenter.getGame(Integer.parseInt(GAMEID_ATTR));
        try {
            board.undoMove();
            message = new Message("Move has been undone", Message.Type.INFO);
        } catch(IllegalStateException e) {
            message = new Message("no Move to be undone", Message.Type.ERROR);

        }
        return gson.toJson(message, Message.class);
    }
}