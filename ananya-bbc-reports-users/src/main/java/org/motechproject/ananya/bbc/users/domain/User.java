package org.motechproject.ananya.bbc.users.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "users_groups",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> groups;


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


    public boolean passwordMatches(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    public List<Role> getRoles() {
        // TODO: refactor to tie to roles via relational mapping
        List<Role> roleList = new ArrayList<Role>();
        for (Group group : groups) {
            for (Role role : group.getRoles()) {
                roleList.add(role);
            }
        }
        return roleList;
    }

    public void addGroup(Group group) {
        if (this.groups == null) this.groups = new ArrayList<Group>();
        this.groups.add(group);
    }
    
    public void addGroups(List<Group> groupList) {
        if (this.groups == null) this.groups = new ArrayList<Group>();
        for (Group group : groupList) {
            this.groups.add(group);
        }
    }
}