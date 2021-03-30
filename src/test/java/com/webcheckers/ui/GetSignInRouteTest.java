package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import spark.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * Tests for GetSignInRoute
 *
 * @author Alex Johannesson
 */

public class GetSignInRouteTest {
    private TemplateEngine templateEngine;
    private GameCenter gameCenter;
    private Session session;
    private Request request;
    private Response response;
    private String person = "TEST";
    private GetSignInRoute CuT;
    private Player player;


    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        gameCenter = new GameCenter();
        templateEngine = mock(TemplateEngine.class);
        response = mock(Response.class);
        player = mock(Player.class);
        person = this.person;
        CuT = new GetSignInRoute(gameCenter, templateEngine);
    }

    @Test
    public void CheckSignIn() throws Exception {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetHomeRoute.CURRENT_USER)).thenReturn(null);
        CuT.handle(request, response);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetSignInRoute.DESCRIPTION);
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_USER, null);
    }

    @Test
    public void FailedSignIn() throws Exception {
        gameCenter.addPlayer(player.getName());
        when(session.attribute(GetHomeRoute.CURRENT_USER)).thenReturn(player);
        try {
            CuT.handle(request,response);
        } catch (HaltException e) {
            //
        }
        verify(response).redirect(WebServer.HOME_URL);

    }
}
