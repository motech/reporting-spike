package org.motechproject.ananya.bbc.web;

public class LinkMenuItem {
    
    private String url;
    
    private String displayString;

    public LinkMenuItem(String displayString, String url) {
        this.displayString = displayString;
        this.url = url;
    }

    public String getDisplayString() {
        return displayString;
    }

    public String getUrl() {
        return url;
    }
}
