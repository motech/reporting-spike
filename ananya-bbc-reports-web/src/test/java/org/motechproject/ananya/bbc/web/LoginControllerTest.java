package org.motechproject.ananya.bbc.web;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class LoginControllerTest {

    @Test
    public void shouldShowLoginPage(){
        LoginController loginController = new LoginController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("login_error")).thenReturn("error");

        ModelAndView modelAndView = loginController.login(request);
        assertThat(modelAndView.getViewName(), is("users/login"));
        assertThat((String) modelAndView.getModelMap().get("error"), is("error"));
    }
}
