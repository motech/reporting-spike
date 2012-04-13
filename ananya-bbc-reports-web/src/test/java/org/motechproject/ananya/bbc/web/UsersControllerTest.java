package org.motechproject.ananya.bbc.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.ananya.bbc.users.service.UserService;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class UsersControllerTest {

    @Mock
    private UserService userService;

    private UsersController usersController;

    @Before
    public void setUp() {
        initMocks(this);
        usersController = new UsersController(userService);
    }

    @Test
    public void shouldFetchAllUsersFromServiceToShowAllUsers() {
        List<UserView> userViews = Arrays.asList(new UserView(1, "username", "name", "admin"));
        when(userService.getUsers()).thenReturn(userViews);

        ModelAndView modelAndView = usersController.listUsers();

        assertThat(modelAndView.getViewName(), is("users/list"));
        assertThat((List<UserView>) modelAndView.getModelMap().get("users"), is(userViews));
    }

    @Test
    public void shouldShowNewUserAddScreen() {
        ModelAndView modelAndView = usersController.newUser();
        assertThat(modelAndView.getViewName(), is("users/new"));
    }

    @Test
    public void shouldFetchUsersFromServiceToShowUserForGivenId() {
        UserView userView = new UserView(1, "username", "name", "admin");
        when(userService.getUser(1)).thenReturn(userView);

        ModelAndView modelAndView = usersController.showUser("1");

        assertThat(modelAndView.getViewName(), is("users/show"));
        assertThat((UserView) modelAndView.getModelMap().get("user"), is(userView));
    }

    @Test
    public void shouldNotAddUserIfUserNameAlreadyExistsInDB() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn("username");
        when(userService.ifUserExistsFor("username")).thenReturn(true);

        ModelAndView modelAndView = usersController.addUser(request);

        assertThat(modelAndView.getViewName(), is("users/new"));
        assertThat((String) modelAndView.getModelMap().get("error"), is("user already exists"));

        verify(userService, never()).createUser(anyString(), anyString(), anyString(), anyList());
    }

    @Test
    public void shouldAddUserIfUserNameDoesNotAlreadyExistsInDB() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("password")).thenReturn("password");
        when(userService.ifUserExistsFor("username")).thenReturn(false);

        ModelAndView modelAndView = usersController.addUser(request);

        assertThat(modelAndView.getViewName(), is("users/show"));

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(userService).createUser(eq("username"), eq("password"), eq("name"), captor.capture());
        List list = captor.getValue();
        assertTrue(list.contains("users"));
    }

    @Test
    public void shouldUpdateUserViaService() {
        UserView userView = new UserView(1, "username", "name", "admin");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("password")).thenReturn("password");
        when(userService.updateUser(1, "name", "password")).thenReturn(userView);

        ModelAndView modelAndView = usersController.updateUser("1", request);

        assertThat(modelAndView.getViewName(), is("users/show"));
        assertThat((UserView) modelAndView.getModelMap().get("user"), is(userView));

    }
}
