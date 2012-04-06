package org.motechproject.ananya.bbc.users;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.MenuLink;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.*;
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
    private AllMenuLinks allMenuLinks;
    
    @Autowired
    private DataAccessTemplate template;

    public void setupBasicUsersGroupsAndRoles() {
        Role role1 = new Role("role1");
        Role role2 = new Role("role2");

        allRoles.add(role1);
        allRoles.add(role2);

        MenuLink menuLinkUsers1 = new MenuLink("Menu Heading 1", "Menu Item 1", "#", 10);
        menuLinkUsers1.setRole(role1);
        MenuLink menuLinkUsers2 = new MenuLink("Menu Heading 1", "Menu Item 2", "#", 20);
        menuLinkUsers2.setRole(role1);

        MenuLink menuLinkReports1 = new MenuLink("Menu Heading 2", "Menu Item 1", "#", 10);
        menuLinkReports1.setRole(role2);

        allMenuLinks.add(menuLinkUsers1);
        allMenuLinks.add(menuLinkUsers2);
        allMenuLinks.add(menuLinkReports1);

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
        List<MenuLink> menuLinkList = allMenuLinks.getAll();
        template.deleteAll(menuLinkList);
    }
}
