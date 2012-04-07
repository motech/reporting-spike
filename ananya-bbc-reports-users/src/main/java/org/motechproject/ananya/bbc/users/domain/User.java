package org.motechproject.ananya.bbc.users.domain;

import org.motechproject.ananya.bbc.users.util.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.username=:username"),
        @NamedQuery(name = User.FIND_BY_USERID, query = "select u from User u where u.id=:id")
})
public class User {

    public static final String FIND_BY_USERNAME = "find.by.username";
    public static final String FIND_BY_USERID = "find.by.userid";
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_groups",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private Set<Group> groups = new HashSet<Group>();

    public User() {}

    public User(String username, String passwordHash, String name) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public List<Role> getRoles() {
        List<Role> roleList = new ArrayList<Role>();

        // TODO: refactor to tie to roles via relational mapping
        // TODO: ensure that there are no duplicate roles.
        for (Group group : groups) {
            for (Role role : group.getRoles()) {
                if (!roleList.contains(role))
                    roleList.add(role);
            }
        }

        return roleList;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
    
    public void addGroups(List<Group> groupList) {
        for (Group group : groupList) {
            this.groups.add(group);
        }
    }

    public List<MenuLink> getMenuLinks() {
        List<MenuLink> menuLinkList = new ArrayList<MenuLink>();

        for (Role role : getRoles()) {
            for(MenuLink menuLink : role.getMenuLinks()) {
                if (!menuLinkList.contains(menuLink))
                    menuLinkList.add(menuLink);
            }
        }
        return menuLinkList;
    }

    public boolean hasPassword(String password){
        return this.passwordHash.equals(Utils.getPasswordHash(password));
    }
}