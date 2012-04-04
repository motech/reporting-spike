package org.motechproject.ananya.bbc.users.seed;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllGroups;
import org.motechproject.ananya.bbc.users.repository.AllRoles;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.util.Utils;
import org.motechproject.deliverytools.seed.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersSeed {
   
    @Autowired
    private AllRoles allRoles;

    @Autowired
    private AllGroups allGroups;

    @Autowired
    private AllUsers allUsers;
    
    @Seed(priority = 0)
    public void loadDefaultUsers() {

        Role role1 = new Role("admin");
        Role role2 = new Role("users");
        
        allRoles.add(role1);
        allRoles.add(role2);

        Group group1 = new Group("admin"); group1.addRole(role1); group1.addRole(role2);
        Group group2 = new Group("users"); group2.addRole(role2);

        allGroups.add(group1);
        allGroups.add(group2);

        User user1 = new User("admin", Utils.getPasswordHash("p@ssw0rd"), "Administrator");
        user1.addGroup(group1);

        allUsers.add(user1);
    }
}
