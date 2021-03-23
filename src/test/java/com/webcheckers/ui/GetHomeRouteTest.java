package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Lobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static com.webcheckers.ui.GetHomeRoute.LOBBY_COUNT;
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

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        templateEngine = mock(TemplateEngine.class);
        gameCenter = new GameCenter();
        lobby = mock(Lobby.class);
        CuT = new GetHomeRoute(gameCenter, templateEngine);
    }

    @Test
    void new_session() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_USER, null);
        testHelper.assertViewModelAttribute(LOBBY_COUNT,lobby.getLobbySize());
        testHelper.assertViewName(GetHomeRoute.VIEW_NAME);

    }


}
