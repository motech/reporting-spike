package org.motechproject.ananya.bbc.users.repository;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllGroups {
   
    @Autowired
    private DataAccessTemplate template;
    
    public void add(Group group) {
        template.save(group);
    }

    public Group findByName(String name) {
        return (Group) template.getUniqueResult(Group.FIND_BY_NAME, new String[]{"name"}, new Object[]{name});
    }

    public List<Group> getAll() {
        return template.loadAll(Group.class);
    }
}
