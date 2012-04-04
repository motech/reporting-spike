package org.motechproject.ananya.bbc.users.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.bbc.users.TestUtils;
import org.motechproject.ananya.bbc.users.exceptions.AuthenticationException;
import org.motechproject.ananya.bbc.users.response.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-Users.xml")
public class AuthenticationServiceTest {

    @Autowired
    private TestUtils testUtils;
    
    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        testUtils.setupBasicUsersGroupsAndRoles();
    }

    @After
    public void tearDown() {
        testUtils.tearDownUsersDB();
    }

    @Test
    public void shouldAuthenticateUserWithCorrectCredentials() throws AuthenticationException {
        RoleResponse roleResponse = authenticationService.authenticateUser("user1", "password");

        assertEquals(roleResponse.getRoles().size(), 2);
        assertTrue(roleResponse.getRoles().contains("role1"));
        assertTrue(roleResponse.getRoles().contains("role2"));
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionIfUserNotExists() throws AuthenticationException {
        authenticationService.authenticateUser("user2", "asd");
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionIfPasswordIsIncorrect() throws AuthenticationException {
        authenticationService.authenticateUser("user1", "asd");
    }
}