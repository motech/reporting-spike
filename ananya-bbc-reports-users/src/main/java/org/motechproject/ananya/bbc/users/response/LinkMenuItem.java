package org.motechproject.ananya.bbc.users.response;

public class LinkMenuItem {
    
    private String displayString;

    private String url;

    private int position;
    
    public LinkMenuItem(String displayString, String url, int position) {
        this.displayString = displayString;
        this.url = url;
        this.position = position;
    }

    public String getDisplayString() {
        return displayString;
    }

    public int getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }
}
