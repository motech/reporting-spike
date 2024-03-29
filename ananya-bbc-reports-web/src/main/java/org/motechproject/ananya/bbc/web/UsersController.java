package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.users.service.UserService;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController {

    private static final List<String> USER_GROUPS = Arrays.asList("users");

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView listUsers() {
        List<UserView> userViews = userService.getUsers();
        return new ModelAndView("users/list").addObject("users", userViews);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public ModelAndView newUser() {
        return new ModelAndView("users/new");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") final String userIdParam) {
        final int userId = Integer.valueOf(userIdParam);

        UserView userView = userService.getUser(userId);
        return new ModelAndView("users/edit").addObject("user", userView);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ModelAndView addUser(HttpServletRequest request) {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");

        if (userService.ifUserExistsFor(username)) {
            ModelAndView modelAndView = new ModelAndView("users/new");
            modelAndView.addObject("name", name);
            modelAndView.addObject("username", username);
            modelAndView.addObject("error", "user already exists");
            return modelAndView;
        }
        UserView userView = userService.createUser(username, password, name, USER_GROUPS);
        return new ModelAndView("users/show")
                .addObject("user", userView)
                .addObject("addUserSuccess", "added new user");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") final String userIdParam, HttpServletRequest request) {
        final int userId = Integer.valueOf(userIdParam);
        final String name = request.getParameter("name");
        final String password = request.getParameter("password");

        UserView userView = userService.updateUser(userId, name, password);
        return new ModelAndView("users/show")
                .addObject("user", userView)
                .addObject("updateUserSuccess", "updated user");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/show/{id}")
    public ModelAndView showUser(@PathVariable("id") final String userIdParam) {
        final int userId = Integer.valueOf(userIdParam);

        UserView userView = userService.getUser(userId);
        return new ModelAndView("users/show").addObject("user", userView);
    }
}
