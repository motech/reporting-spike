package org.motechproject.ananya.bbc.users.views;

import java.util.*;

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
        List<LinkMenuView> linkMenuViews = menuMap.get(menuHeading);
        linkMenuViews.add(linkMenuItem);
        Collections.sort(linkMenuViews);
    }

    public Map<String, List<LinkMenuView>> getMenuMap() {
        return menuMap;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{roles=" + roles + "|menuMap=" + menuMap + " }";
    }
}
