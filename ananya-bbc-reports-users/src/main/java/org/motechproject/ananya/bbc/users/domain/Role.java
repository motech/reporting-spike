package org.motechproject.ananya.bbc.users.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    
    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
