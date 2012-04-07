package org.motechproject.ananya.bbc.users.seed;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.MenuLink;
import org.motechproject.ananya.bbc.users.domain.Role;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllGroups;
import org.motechproject.ananya.bbc.users.repository.AllMenuLinks;
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

    @Autowired
    private AllMenuLinks allMenuLinks;

    @Seed(priority = 0)
    public void loadDefaultUsers() {

        Role role1 = new Role("user_management");
        Role role2 = new Role("reports");
        
        allRoles.add(role1);
        allRoles.add(role2);

        MenuLink menuLinkUsers1 = new MenuLink("Users", "Register new user", "users/new", 10);
        menuLinkUsers1.setRole(role1);
        MenuLink menuLinkUsers2 = new MenuLink("Users", "Users", "users/list", 20);
        menuLinkUsers2.setRole(role1);

        MenuLink menuLinkReports1 = new MenuLink("Reports", "Certificate Course Usage Report", "report/certificatecourse", 10);
        menuLinkReports1.setRole(role2);

        allMenuLinks.add(menuLinkUsers1);
        allMenuLinks.add(menuLinkUsers2);
        allMenuLinks.add(menuLinkReports1);

        Group group1 = new Group("admin");
        group1.addRole(role1);
        group1.addRole(role2);
        Group group2 = new Group("users");
        group2.addRole(role2);

        allGroups.add(group1);
        allGroups.add(group2);

        User user1 = new User("admin", Utils.getPasswordHash("p@ssw0rd"), "Administrator");
        user1.addGroup(group1);

        allUsers.add(user1);
    }
}
