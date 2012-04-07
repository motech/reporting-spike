package org.motechproject.ananya.bbc.users.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationResponse {

    private List<String> roles = new ArrayList<String>();

    private Map<String, List<LinkMenuItem>> menuMap = new HashMap<String, List<LinkMenuItem>>();

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String roleName) {
        this.roles.add(roleName);
    }

    public void addLinkMenuItem(String menuHeading, LinkMenuItem linkMenuItem) {
        if (!menuMap.containsKey(menuHeading))
            menuMap.put(menuHeading, new ArrayList<LinkMenuItem>());
        menuMap.get(menuHeading).add(linkMenuItem);
    }

    public Map<String, List<LinkMenuItem>> getMenuMap() {
        return menuMap;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{roles=" + roles + "|menuMap=" + menuMap + " }";
    }
}
