package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.security.AuthenticatedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        final String error = request.getParameter("login_error");
        return new ModelAndView("users/login").addObject("error", error);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ModelAndView test(HttpServletRequest request) {
        AuthenticatedUser user = loggedInUser(request);
        log.info("Logged in: " + user + "  " + user.getUsername());
        return new ModelAndView("base").addObject("menuMap", user.getMenuMap()).addObject("username", user.getUsername());
    }

}
