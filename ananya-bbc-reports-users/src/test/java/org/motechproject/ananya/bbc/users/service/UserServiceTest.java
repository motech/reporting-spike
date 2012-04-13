package org.motechproject.ananya.bbc.users.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.bbc.users.TestUtils;
import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.util.Utils;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-users.xml")
public class UserServiceTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AllUsers allUsers;

    @Before
    public void setUp() {
        testUtils.setupBasicUsersGroupsAndRoles();
    }

    @After
    public void tearDown() {
        testUtils.tearDownUsersDB();
    }

    @Test
    public void shouldCreateUserWithGroup() {
        String username = "my_user";
        String name = "Name";
        String password = "my_password";
        String group1 = "group1";
        String group2 = "group2";

        UserView userResponse = userService.createUser(username, password, name, Arrays.asList(group1, group2));

        assertEquals(username, userResponse.getUsername());
        assertEquals(name, userResponse.getName());

        User user = allUsers.findByUserId(userResponse.getId());

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(name, user.getName());
        assertTrue(user.hasPassword(password));
        assertEquals(2, user.getGroups().size());

        List<String> groupNames = new ArrayList<String>();
        for (Group group : user.getGroups()) {
            groupNames.add(group.getName());
        }

        assertTrue(groupNames.contains(group1));
        assertTrue(groupNames.contains(group2));
    }

    @Test
    public void shouldUpdateUserWithoutPasswordIfThePasswordFieldIsBlank() {
        String newName = "new name";
        String password = "password";
        User user = new User("user", Utils.getPasswordHash(password), "name");
        allUsers.add(user);

        UserView userResponse = userService.updateUser(user.getId(), newName, "");
        assertEquals(newName, userResponse.getName());

        User updatedUser = allUsers.findByUserId(user.getId());
        assertEquals(newName, updatedUser.getName());
        assertTrue(updatedUser.hasPassword(password));
    }

    @Test
    public void shouldUpdateUserWithPasswordIfThePasswordFieldIsNotBlank() {
        String newName = "new name";
        User user = new User("user", Utils.getPasswordHash("password"), "name");
        allUsers.add(user);

        String newPassword = "newPassword";
        UserView userResponse = userService.updateUser(user.getId(), newName, newPassword);
        assertEquals(newName, userResponse.getName());

        User updatedUser = allUsers.findByUserId(user.getId());
        assertEquals(newName, updatedUser.getName());
        assertTrue(updatedUser.hasPassword(newPassword));
    }

    @Test
    public void shouldUpdateUserWithUserNameAndPasswordGivenTheUsername() {
        String username = "user";
        User user = new User(username, Utils.getPasswordHash("password"), "name");
        allUsers.add(user);

        String newPassword = "newPassword";
        userService.updateUser(username, newPassword);

        User updatedUser = allUsers.findByUserId(user.getId());
        assertTrue(updatedUser.hasPassword(newPassword));
    }

    @Test
    public void shouldGetAllUsersFromTheDB() {
        User user = new User("user", Utils.getPasswordHash("password"), "name");
        allUsers.add(user);

        List<UserView> userResponseList = userService.getUsers();

        assertEquals(2, userResponseList.size());
        assertEquals("user1", userResponseList.get(0).getUsername());
        assertEquals("user", userResponseList.get(1).getUsername());
    }

    @Test
    public void shouldGetUserFromDB() {
        User user = new User("user", Utils.getPasswordHash("password"), "name");
        allUsers.add(user);

        UserView userResponse = userService.getUser(user.getId());

        assertEquals(user.getId(), userResponse.getId());
        assertEquals(user.getName(), userResponse.getName());
        assertEquals(user.getUsername(), userResponse.getUsername());

    }

    @Test
    public void shouldGetUserByUserName() {
        String username = "username";
        String name = "name";
        User user = new User(username, Utils.getPasswordHash("password"), name);
        allUsers.add(user);

        UserView userByUserName = userService.getUserByUserName(username);

        assertEquals(username, userByUserName.getUsername());
        assertEquals(name, userByUserName.getName());
    }

    @Test
    public void shouldSayThatTheUserIsInvalidIfPasswordDoesNotMatch() {
        String username = "username";
        String name = "name";
        String password = "password";
        String invalidPassword = "invalidPassword";
        User user = new User(username, Utils.getPasswordHash(password), name);
        allUsers.add(user);

        boolean notValidUser = userService.isNotValidUser(username, invalidPassword);

        assertTrue(notValidUser);
    }

    @Test
    public void shouldSayThatTheUserIsValidIfPasswordsMatch() {
        String username = "username";
        String name = "name";
        String password = "password";
        User user = new User(username, Utils.getPasswordHash(password), name);
        allUsers.add(user);

        boolean notValidUser = userService.isNotValidUser(username, password);

        assertFalse(notValidUser);
    }
}
