package org.motechproject.ananya.bbc.security;

import org.motechproject.ananya.bbc.users.exceptions.AuthenticationException;
import org.motechproject.ananya.bbc.users.response.RoleResponse;
import org.motechproject.ananya.bbc.users.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
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
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) 
            throws org.springframework.security.core.AuthenticationException {
        log.info("LOG_CHK : I am here " + username);
        String password = (String) authentication.getCredentials();

        RoleResponse roleResponse;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        try {
            log.info("LOG_CHK : " + username + " " + password);
            roleResponse = authenticationService.authenticateUser(username, password);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        for(String role : roleResponse.getRoles()) {
            grantedAuthorityList.add(new GrantedAuthorityImpl(role));
        }

        return new User(username, password, true, true, true, true, grantedAuthorityList);
    }
}
