package org.motechproject.ananya.bbc.security;

import org.motechproject.ananya.bbc.users.views.AuthenticationResponse;
import org.motechproject.ananya.bbc.users.views.LinkMenuView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;

public class AuthenticatedUser extends User {

    private Map<String, List<LinkMenuView>> menuMap;

    public AuthenticatedUser(AuthenticationResponse authenticationResponse,
                             List<GrantedAuthority> grantedAuthorityList,
                             String username,
                             String password) {
        super(username, password, true, true, true, true, grantedAuthorityList);
        this.menuMap = authenticationResponse.getMenuMap();
    }

    public Map<String, List<LinkMenuView>> getMenuMap() {
        return menuMap;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{menuMap=" + menuMap + '}';
    }
}
