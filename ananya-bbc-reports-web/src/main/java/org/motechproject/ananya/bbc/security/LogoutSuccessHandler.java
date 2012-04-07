package org.motechproject.ananya.bbc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            final HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(LoginSuccessHandler.LOGGED_IN_USER);
                session.invalidate();
            }
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}
