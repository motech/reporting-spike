package org.motechproject.ananya.bbc.users.repository;

import org.motechproject.ananya.bbc.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllUsers {

    @Autowired
    private DataAccessTemplate template;

    public void add(User user) {
        template.save(user);
    }

    public User findByUsername(String username) {
        return (User) template.getUniqueResult(User.FIND_BY_USERNAME, new String[]{"username"}, new Object[]{username});
    }

    public User findByUserId(int id) {
        return (User) template.getUniqueResult(User.FIND_BY_USERID, new String[]{"id"}, new Object[]{id});
    }

    public void update(User existingUser) {
        template.update(existingUser);
    }
    
    public List<User> getAll() {
        return template.loadAll(User.class);
    }
}
