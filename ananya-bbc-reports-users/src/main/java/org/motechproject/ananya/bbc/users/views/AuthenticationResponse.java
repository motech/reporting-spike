package org.motechproject.ananya.bbc.users.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationResponse {

    private List<String> roles = new ArrayList<String>();

    private Map<String, List<LinkMenuView>> menuMap = new HashMap<String, List<LinkMenuView>>();

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String roleName) {
        this.roles.add(roleName);
    }

    public void addLinkMenuItem(String menuHeading, LinkMenuView linkMenuItem) {
        if (!menuMap.containsKey(menuHeading))
            menuMap.put(menuHeading, new ArrayList<LinkMenuView>());
        menuMap.get(menuHeading).add(linkMenuItem);
    }

    public Map<String, List<LinkMenuView>> getMenuMap() {
        return menuMap;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{roles=" + roles + "|menuMap=" + menuMap + " }";
    }
}
