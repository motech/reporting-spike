package org.motechproject.ananya.bbc.users.repository;

import org.motechproject.ananya.bbc.users.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllRoles {

    @Autowired
    private DataAccessTemplate template;    
    
    public void add(Role role) {
        template.save(role);
    }

    public List<Role> getAll() {
        return template.findByExample(new Role()); //matches all roles
    }
}
