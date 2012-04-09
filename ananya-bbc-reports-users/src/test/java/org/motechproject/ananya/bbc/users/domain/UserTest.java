package org.motechproject.ananya.bbc.users.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UserTest{

    @Test
    public void shouldReturnRoleTypeAdminForGroupTypeAdmin() {
        User user = new User("raja", "ab667fffe", "Raja Rao");
        user.addGroup(new Group("admin"));
        user.addGroup(new Group("other_group"));

        assertEquals("Admin", user.getRoleType());
    }

    @Test
    public void shouldReturnRoleTypeUserIfGroupTypeNotAdmin() {
        User user = new User("raja", "ab667fffe", "Raja Rao");
        user.addGroup(new Group("other_group1"));
        user.addGroup(new Group("other_group2"));

        assertEquals("User", user.getRoleType());
    }
}
