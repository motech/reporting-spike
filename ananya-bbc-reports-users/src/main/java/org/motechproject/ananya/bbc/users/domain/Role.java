package org.motechproject.ananya.bbc.users.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Group> groups = new HashSet<Group>();
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Set<MenuLink> menuLinks = new HashSet<MenuLink>();
    
    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<MenuLink> getMenuLinks() {
        return menuLinks;
    }
}
