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


    @BeforeEach
    public void GetSignInRoute() {
        gameCenter = new GameCenter();
        templateEngine = mock(TemplateEngine.class);
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        person = this.person;

    }

    @Test
    public void CheckSignIn() throws Exception {
        when(session.attribute(GetHomeRoute.CURRENT_USER)).thenReturn(null);
        final TemplateEngineTester helper = new TemplateEngineTester();
        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();
        helper.assertViewName(GetSignInRoute.VIEW_NAME);
    }

    @Test
    public void FailedSignIn() throws Exception {
        gameCenter.addPlayer(this.person);
        Player player = gameCenter.getPlayer(this.person);
        doReturn(player).when(session.attribute(GetHomeRoute.CURRENT_USER));
        verify(response).redirect(WebServer.HOME_URL);

    }

}
