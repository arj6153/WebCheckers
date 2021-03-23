package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import java.awt.*;

import static com.webcheckers.ui.GetHomeRoute.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


public class GetHomeRouteTest {

    private GetHomeRoute CuT;
    private GameCenter gameCenter;
    private Request request;
    private TemplateEngine templateEngine;
    private Player player;
    private Lobby lobby;
    private Response response;
    private Session session;
    private Message message;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        templateEngine = mock(TemplateEngine.class);
        gameCenter = new GameCenter();
        lobby = gameCenter.getLobby();
        CuT = new GetHomeRoute(gameCenter, templateEngine);
    }

    @Test
    void new_session() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(CURRENT_USER)).thenReturn(null);
        CuT.handle(request, response);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_USER, null);
        testHelper.assertViewModelAttribute(LOBBY_COUNT,lobby.getLobbySize());
        testHelper.assertViewName(GetHomeRoute.VIEW_NAME);

    }

    @Test
    void old_session() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = mock(Player.class);
        gameCenter.addPlayer(player.getName());
        when(session.attribute(CURRENT_USER)).thenReturn(player);
        message = new Message("Test", Message.Type.INFO);
        when(session.attribute(MESSAGE_ATTR)).thenReturn(message);
        CuT.handle(request, response);
        assertFalse(player.isPlaying());
        testHelper.assertViewModelAttribute(MESSAGE_ATTR, message);
        testHelper.assertViewModelAttribute(CURRENT_USER, player);
        testHelper.assertViewModelAttribute(PLAYER_LIST_ATTR, lobby.getMap());


    }


}
