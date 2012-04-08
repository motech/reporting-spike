package org.motechproject.ananya.bbc.users.service;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllGroups;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.views.UserView;
import org.motechproject.ananya.bbc.users.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AllUsers allUsers;

    @Autowired
    private AllGroups allGroups;

    public UserView createUser(String username, String password, String name, List<String> groupNames) {
        List<Group> groups = new ArrayList<Group>();
        for (String groupName : groupNames)
            groups.add(allGroups.findByName(groupName));

        User user = new User(username, Utils.getPasswordHash(password), name);
        user.addGroups(groups);
        allUsers.add(user);
        return getResponseForUser(user);
    }

    public UserView updateUser(int userId, String name, String password) {
        User existingUser = allUsers.findByUserId(userId);
        existingUser.setName(name);
        existingUser.setPassword(Utils.getPasswordHash(password));
        allUsers.update(existingUser);
        return getResponseForUser(existingUser);
    }

    public List<UserView> getUsers() {
        List<User> userList = allUsers.getAll();
        List<UserView> userResponses = new ArrayList<UserView>();
        for (User user : userList)
            userResponses.add(getResponseForUser(user));
        return userResponses;
    }

    public UserView getUser(Integer userId) {
        User user = allUsers.findByUserId(userId);
        return getResponseForUser(user);
    }

    private UserView getResponseForUser(User user) {
        return new UserView(user.getId(), user.getUsername(), user.getName());
    }

    public boolean ifUserExistsFor(String username) {
        return allUsers.findByUsername(username) != null;
    }
}
