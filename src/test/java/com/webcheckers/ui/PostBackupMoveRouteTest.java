package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Tag("UI-tier")
class PostBackupMoveRouteTest {
    private PostBackupMoveRoute BackUpMoveRoute;

    private GameCenter gameCenter;
    private Game game;
    private BoardView board;
    private static final String PLAYER1 = "test";
    private static final String PLAYER2 = "opponent";
    private Player player;
    private Player opponent;
    private Request request;
    private Session session;
    private Response response;
    private Gson gson;
    private String gameID;

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
        gameID = UUID.randomUUID().toString();
        gameCenter.addGame(gameCenter.getPlayer(PLAYER1), gameCenter.getPlayer(PLAYER2));
        player = gameCenter.getPlayer(PLAYER1);
        player.setPlaying(true);

        opponent = gameCenter.getPlayer(PLAYER2);
        opponent.setPlaying(true);

        //creating and adding the game with gameID into the game center
        gameID = UUID.randomUUID().toString();
        gameCenter.addGame(player, opponent);
        game = gameCenter.getGame(Integer.parseInt(gameID));
        when(request.queryParams(GetGameRoute.GAMEID_ATTR)).thenReturn(gameID);

        BackUpMoveRoute = new PostBackupMoveRoute(gameCenter, gson);
    }

    @Test
    public void backupSuccess() throws Exception {

        if (game.undoMove() == true)
            when(request.session().attribute(GetHomeRoute.CURRENT_USER)).thenReturn(PLAYER1);
        BackUpMoveRoute.handle(request, response);
    }
}