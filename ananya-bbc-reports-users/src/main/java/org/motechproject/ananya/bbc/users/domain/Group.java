package org.motechproject.ananya.bbc.users.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "groups_roles",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    public Group() { }
    
    public Group(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (this.roles == null) this.roles = new ArrayList<Role>();
        this.roles.add(role);
    }
}
