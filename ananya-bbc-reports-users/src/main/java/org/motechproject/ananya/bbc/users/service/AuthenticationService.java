package org.motechproject.ananya.bbc.users.service;

import org.motechproject.ananya.bbc.users.domain.MenuLink;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.exceptions.AuthenticationException;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.response.AuthenticationResponse;
import org.motechproject.ananya.bbc.users.response.LinkMenuItem;
import org.motechproject.ananya.bbc.users.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private AllUsers allUsers;

    private final String USER_NOT_EXISTS_ERROR = "User doesn't exist";
    private final String INVALID_PASSWORD_ERROR = "Incorrect password";

    public AuthenticationResponse authenticateUser(String username, String password) throws AuthenticationException {
        User user = allUsers.findByUsername(username);
        
        if (user == null) throw new AuthenticationException(USER_NOT_EXISTS_ERROR);
        
        if (!user.passwordMatches(Utils.getPasswordHash(password))) throw new AuthenticationException(INVALID_PASSWORD_ERROR);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        List<Role> roleList = user.getRoles();
        for (Role role : roleList) {
            authenticationResponse.addRole(role.getName());
        }
        
        List<MenuLink> menuLinkList = user.getMenuLinks();
        for (MenuLink menuLink : menuLinkList) {
            authenticationResponse.addLinkMenuItem(
                    menuLink.getMenuHeading(),
                    new LinkMenuItem(menuLink.getDisplayString(), menuLink.getUrl(), menuLink.getPosition())
            );
        }

        return authenticationResponse;
    }
}
