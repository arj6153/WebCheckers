package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostResignRouteTest {
    private Request request;
    private Session session;
    private Response response;
    private GameCenter gameCenter;
    private String gameID;
    private Game game;
    private PostResignRoute resignRoute;
    private static final String PLAYER1 = "test";
    private static final String PLAYER2 = "opponent";
    private Gson gson;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gameCenter = new GameCenter();
        gson = new Gson();
        gameCenter.addPlayer(PLAYER1);
        gameCenter.addPlayer(PLAYER2);
        Player player1 = gameCenter.getPlayer(PLAYER1);
        gameCenter.addGame(gameCenter.getPlayer(PLAYER1), gameCenter.getPlayer(PLAYER2));
        game = gameCenter.getGame(player1);
        when(request.queryParams(GetGameRoute.GAMEID_ATTR)).thenReturn(gameID);
        resignRoute = new PostResignRoute(gameCenter, gson);
    }

}
