package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import javax.swing.text.DefaultEditorKit;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * Tests for GetSignInRoute
 *
 * @author Alex Johannesson
 */

public class GetSignInRouteTester {
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
        gameCenter = new GameCenter();
        templateEngine = mock(TemplateEngine.class);
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
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
        testHelper.assertViewName(GetSignInRoute.VIEW_NAME);
    }

    @Test
    public void FailedSignIn() throws Exception {
        CuT.handle(request, response);
        verify(response, never()).redirect(any());
    }
}
