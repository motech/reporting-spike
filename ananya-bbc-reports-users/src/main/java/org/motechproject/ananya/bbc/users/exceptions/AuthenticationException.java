package org.motechproject.ananya.bbc.users.exceptions;

public class AuthenticationException extends AnanyaBbcUsersException {
    public AuthenticationException(String error) {
        super(error);
    }
}