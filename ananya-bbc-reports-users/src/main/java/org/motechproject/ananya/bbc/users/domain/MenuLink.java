package org.motechproject.ananya.bbc.users.domain;

import javax.persistence.*;

@Entity
@Table(name = "menu_links")
public class MenuLink {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_heading")
    private String menuHeading;
    
    @Column(name = "display_string")
    private String displayString;

    @Column(name = "url")
    private String url;

    @Column(name = "position")
    private int position;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public MenuLink() { }

    public MenuLink(String menuHeading, String displayString, String url, int position) {
        this.menuHeading = menuHeading;
        this.displayString = displayString;
        this.url = url;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getMenuHeading() {
        return menuHeading;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
