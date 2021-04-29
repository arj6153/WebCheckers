
package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostCheckTurnRouteTest {

    private PostCheckTurnRoute CheckTurnRoute;

    private static final String PLAYER1 = "test";
    private static final String PLAYER2 = "opponent";
    private GameCenter gameCenter;
    private String gameID;
    private Game game;

    private Request request;
    private Response response;
    private Session session;
    private Gson gson;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gson = new Gson();
        gameCenter = new GameCenter();
        gameCenter.addPlayer(PLAYER1);
        gameCenter.addPlayer(PLAYER2);
        Player player1 = gameCenter.getPlayer(PLAYER1);
        gameCenter.addGame(gameCenter.getPlayer(PLAYER1), gameCenter.getPlayer(PLAYER2));
        game = gameCenter.getGame(player1);
        when(request.session().attribute(GetHomeRoute.CURRENT_USER)).thenReturn(gameCenter.getPlayer(PLAYER1));
        when(request.queryParams(GetGameRoute.GAMEID_ATTR)).thenReturn(gameID);
        CheckTurnRoute = new PostCheckTurnRoute(gameCenter, gson);
    }

}
