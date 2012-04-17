package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.security.AuthenticatedUser;
import org.motechproject.ananya.bbc.users.service.UserService;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends BaseController {
    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView showHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/change_password")
    public ModelAndView changePassword(HttpServletRequest request) {
        UserView userView = getAuthenticatedUser(request);
        return new ModelAndView("changepassword").addObject("user", userView);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update_password")
    public ModelAndView updatePassword(HttpServletRequest request) {
        final String oldPassword = request.getParameter("oldPassword");
        final String newPassword = request.getParameter("password");
        UserView userView = getAuthenticatedUser(request);
        final String username = userView.getUsername();

        if (userService.isNotValidUser(username, oldPassword))
            return new ModelAndView("changepassword").addObject("error", "wrong password").addObject("user", userView);

        userService.updateUser(username, newPassword);
        return new ModelAndView("changepassword").addObject("success", "updated password").addObject("user", userView);
    }

    private UserView getAuthenticatedUser(HttpServletRequest request) {
        AuthenticatedUser authenticatedUser = loggedInUser(request);
        return userService.getUserByUserName(authenticatedUser.getUsername());
    }
}
