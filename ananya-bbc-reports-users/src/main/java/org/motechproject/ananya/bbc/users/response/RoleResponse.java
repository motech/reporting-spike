package org.motechproject.ananya.bbc.users.response;

import java.util.ArrayList;
import java.util.List;

public class RoleResponse {
    
    private List<String> roles = new ArrayList<String>();

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String roleName) {
        this.roles.add(roleName);
    }
}
