package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.security.AuthenticatedUser;
import org.motechproject.ananya.bbc.security.LoginSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected AuthenticatedUser loggedInUser(HttpServletRequest request) {
        AuthenticatedUser user =
                (AuthenticatedUser) request.getSession().getAttribute(LoginSuccessHandler.LOGGED_IN_USER);
        return user;
    }
    
}
