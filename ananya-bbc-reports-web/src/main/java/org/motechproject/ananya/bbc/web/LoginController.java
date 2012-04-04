package org.motechproject.ananya.bbc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        final String error = request.getParameter("login_error");

        return new ModelAndView("login").addObject("error", error);
    }

}
