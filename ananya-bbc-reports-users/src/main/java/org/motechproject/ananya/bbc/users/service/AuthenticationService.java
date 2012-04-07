package org.motechproject.ananya.bbc.users.service;

import org.motechproject.ananya.bbc.users.domain.MenuLink;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.exceptions.AnanyaAuthenticationException;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.response.AuthenticationResponse;
import org.motechproject.ananya.bbc.users.response.LinkMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final String INVALID_PASSWORD_ERROR = "Incorrect password";
    private final String USER_NOT_EXISTS_ERROR = "User does not exist";

    @Autowired
    private AllUsers allUsers;

    public AuthenticationResponse authenticateUser(String username, String password) throws AnanyaAuthenticationException {

        User user = allUsers.findByUsername(username);
        if (user == null)
            throw new AnanyaAuthenticationException(USER_NOT_EXISTS_ERROR);
        if (!user.hasPassword(password))
            throw new AnanyaAuthenticationException(INVALID_PASSWORD_ERROR);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        List<Role> roleList = user.getRoles();
        for (Role role : roleList)
            authenticationResponse.addRole(role.getName());

        List<MenuLink> menuLinks = user.getMenuLinks();
        for (MenuLink menuLink : menuLinks)
            authenticationResponse.addLinkMenuItem(
                    menuLink.getMenuHeading(),
                    new LinkMenuItem(menuLink.getDisplayString(), menuLink.getUrl(), menuLink.getPosition())
            );
        return authenticationResponse;
    }
}
