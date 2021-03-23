package com.webcheckers.ui;

import static org.mockito.Mockito.*;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import javax.swing.*;

@Tag("UI-Tier")
public class GetSignOutRouteTest {

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine templateEngine;
    private Game game;
    private Player player;
    private GameCenter gameCenter;
    private PostSignOutRoute getSignOutRoute;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gameCenter = mock(GameCenter.class);
        templateEngine = mock(TemplateEngine.class);
        getSignOutRoute = new PostSignOutRoute(gameCenter);
    }

//    @Test
//    public void valid_home() {
//        when(request.session().attributes(PostSignInRoute)).
//                thenReturn(player);
//        when(game.isPlayerInGame(player))
   // }
}
