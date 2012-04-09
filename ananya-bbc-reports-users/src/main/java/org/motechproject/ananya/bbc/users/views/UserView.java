package org.motechproject.ananya.bbc.users.views;

public class UserView {

    private Integer id;

    private String username;

    private String name;

    private String roleType;

    public UserView(Integer id, String username, String name, String roleType) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roleType = roleType;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getRoleType() {
        return roleType;
    }
}
