package org.motechproject.ananya.bbc.security;

import org.motechproject.ananya.bbc.users.exceptions.AnanyaAuthenticationException;
import org.motechproject.ananya.bbc.users.response.AuthenticationResponse;
import org.motechproject.ananya.bbc.users.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static Logger log = LoggerFactory.getLogger(AuthenticationProvider.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws org.springframework.security.core.AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();
        AuthenticationResponse authenticationResponse;
        try {
            authenticationResponse = authenticationService.authenticateUser(username, password);
        } catch (AnanyaAuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (String role : authenticationResponse.getRoles())
            grantedAuthorities.add(new GrantedAuthorityImpl(role));

        log.info("Authentication Response is " + authenticationResponse.toString());
        return new AuthenticatedUser(authenticationResponse, grantedAuthorities, username, password);
    }
}
