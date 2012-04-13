package org.motechproject.ananya.bbc.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.motechproject.ananya.bbc.security.AuthenticatedUser;
import org.motechproject.ananya.bbc.security.LoginSuccessHandler;
import org.motechproject.ananya.bbc.users.service.UserService;
import org.motechproject.ananya.bbc.users.views.AuthenticationResponse;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HomeControllerTest {

    @Mock
    HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    private HomeController homeController;

    @Before
    public void setUp() {
        initMocks(this);
        homeController = new HomeController(userService);
    }

    @Test
    public void shouldReturnHomeView() {
        ModelAndView modelAndView = homeController.showHome();

        assertThat(modelAndView.getViewName(), is("home"));
    }

    @Test
    public void shouldReturnChangePasswordWithTheAppropriateUserObject() {
        String username = "username";
        UserView userView = new UserView(null, null, null, null);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LoginSuccessHandler.LOGGED_IN_USER)).thenReturn(
                new AuthenticatedUser(new AuthenticationResponse(),
                        new ArrayList<GrantedAuthority>(), username, "pass"));
        when(userService.getUserByUserName(username)).thenReturn(userView);

        ModelAndView modelAndView = homeController.changePassword(request);

        assertEquals(modelAndView.getViewName(), "changepassword");
        assertEquals(modelAndView.getModel().get("user"), userView);
    }

    @Test
    public void shouldReturnErrorIfOldPasswordIsInvalidWhenSavingTheNewPassword() {
        String username = "username";
        String incorrectPassword = "pass";
        UserView userView = new UserView(null, username, null, null);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("oldPassword")).thenReturn(incorrectPassword);
        when(session.getAttribute(LoginSuccessHandler.LOGGED_IN_USER)).thenReturn(
                new AuthenticatedUser(new AuthenticationResponse(),
                        new ArrayList<GrantedAuthority>(), username, incorrectPassword));
        when(userService.getUserByUserName(username)).thenReturn(userView);
        when(userService.isNotValidUser(username, incorrectPassword)).thenReturn(true);

        ModelAndView modelAndView = homeController.updatePassword(request);

        assertEquals(modelAndView.getViewName(), "changepassword");
        assertEquals(modelAndView.getModel().get("user"), userView);
        assertNotNull(modelAndView.getModel().get("error"));
    }

    @Test
    public void shouldRedirectToHomePageAfterSavingTheNewPassword() {
        String username = "username";
        String password = "pass";
        String newPassword = "newPassword";
        UserView userView = new UserView(null, username, null, null);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("oldPassword")).thenReturn(password);
        when(request.getParameter("password")).thenReturn(newPassword);
        when(session.getAttribute(LoginSuccessHandler.LOGGED_IN_USER)).thenReturn(
                new AuthenticatedUser(new AuthenticationResponse(),
                        new ArrayList<GrantedAuthority>(), username, password));
        when(userService.getUserByUserName(username)).thenReturn(userView);
        when(userService.isNotValidUser(username, password)).thenReturn(false);

        ModelAndView modelAndView = homeController.updatePassword(request);

        assertEquals(modelAndView.getViewName(), "home");
        verify(userService).updateUser(username, newPassword);
    }
}
