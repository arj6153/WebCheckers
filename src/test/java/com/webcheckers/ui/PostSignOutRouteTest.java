package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@Tag("UI-Tier")
public class PostSignOutRouteTest {

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine templateEngine;
    private Game game;
    private Player player;
    private GameCenter gameCenter;
    private PostSignOutRoute CuT;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gameCenter = mock(GameCenter.class);
        player = mock(Player.class);
        templateEngine = mock(TemplateEngine.class);
        CuT = new PostSignOutRoute(gameCenter);
    }

    @Test
    public void valid_home()  {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetHomeRoute.CURRENT_USER)).thenReturn(player);
        try {
            CuT.handle(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(gameCenter.getPlayer(player.getName()));
        assertTrue(session.attributes().isEmpty());
    }
}
