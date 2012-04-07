package org.motechproject.ananya.bbc.users.views;

public class UserView {
    
    private int id;
    
    private String username;
    
    private String name;

    public UserView(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
