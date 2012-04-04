package org.motechproject.ananya.bbc.users;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllGroups;
import org.motechproject.ananya.bbc.users.repository.AllRoles;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.repository.DataAccessTemplate;
import org.motechproject.ananya.bbc.users.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestUtils {

    @Autowired
    private AllUsers allUsers;

    @Autowired
    private AllGroups allGroups;

    @Autowired
    private AllRoles allRoles;

    @Autowired
    private DataAccessTemplate template;

    public void setupBasicUsersGroupsAndRoles() {
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");

        allRoles.add(role1);
        allRoles.add(role2);

        Group group1 = new Group("group1"); group1.addRole(role1); group1.addRole(role2);
        Group group2 = new Group("group2"); group2.addRole(role2);

        allGroups.add(group1);
        allGroups.add(group2);

        User user1 = new User("user1", Utils.getPasswordHash("password"), "User");
        user1.addGroup(group1);

        allUsers.add(user1);
    }
    
    public void tearDownUsersDB() {
        List<User> userList = allUsers.getAll();
        template.deleteAll(userList);
        List<Group> groupList = allGroups.getAll();
        template.deleteAll(groupList);
        List<Role> roleList = allRoles.getAll();
        template.deleteAll(roleList);
    }
}
