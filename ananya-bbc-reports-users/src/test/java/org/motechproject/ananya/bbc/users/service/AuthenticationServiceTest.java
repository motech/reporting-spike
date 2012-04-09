package org.motechproject.ananya.bbc.users.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.bbc.users.TestUtils;
import org.motechproject.ananya.bbc.users.exceptions.AnanyaAuthenticationException;
import org.motechproject.ananya.bbc.users.views.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-users.xml")
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
    public void shouldAuthenticateUserWithCorrectCredentials() throws AnanyaAuthenticationException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticateUser("user1", "password");

        assertEquals(authenticationResponse.getRoles().size(), 2);
        assertTrue(authenticationResponse.getRoles().contains("role1"));
        assertTrue(authenticationResponse.getRoles().contains("role2"));
        assertTrue(authenticationResponse.getMenuMap().containsKey("Menu Heading 1"));
        assertTrue(authenticationResponse.getMenuMap().containsKey("Menu Heading 2"));
        assertTrue(authenticationResponse.getMenuMap().get("Menu Heading 1").size() == 2);
        assertTrue(authenticationResponse.getMenuMap().get("Menu Heading 2").size() == 1);
    }

    @Test(expected = AnanyaAuthenticationException.class)
    public void shouldThrowExceptionIfUserNotExists() throws AnanyaAuthenticationException {
        authenticationService.authenticateUser("user2", "asd");
    }

    @Test(expected = AnanyaAuthenticationException.class)
    public void shouldThrowExceptionIfPasswordIsIncorrect() throws AnanyaAuthenticationException {
        authenticationService.authenticateUser("user1", "asd");
    }
}
