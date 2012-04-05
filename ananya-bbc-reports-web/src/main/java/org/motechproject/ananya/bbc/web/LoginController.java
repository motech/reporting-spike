package org.motechproject.ananya.bbc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        final String error = request.getParameter("login_error");

        return new ModelAndView("login").addObject("error", error);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ModelAndView test(HttpServletRequest request) {

        Map<String, List<LinkMenuItem>> menuMap = new HashMap<String, List<LinkMenuItem>>();
        menuMap.put("Users", Arrays.asList(new LinkMenuItem[]{new LinkMenuItem("Register new user", "/register"), new LinkMenuItem("Users", "/list")}));
        menuMap.put("Reports", Arrays.asList(new LinkMenuItem[]{new LinkMenuItem("CC Usage Report", "/reports")}));

        return new ModelAndView("base").addObject("menuMap", menuMap);
    }

}
