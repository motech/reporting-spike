package org.motechproject.ananya.bbc.users.domain;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
@NamedQueries(value = {
        @NamedQuery(name = Group.FIND_BY_NAME, query = "select g from Group g where g.name=:name")
})
public class Group {

    public static final String FIND_BY_NAME = "find.by.name";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "groups_roles",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<User>();

    public Group() { }
    
    public Group(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public String getName() {
        return name;
    }
}
