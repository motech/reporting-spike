package org.motechproject.ananya.bbc.users.service;

import org.motechproject.ananya.bbc.users.domain.Group;
import org.motechproject.ananya.bbc.users.domain.User;
import org.motechproject.ananya.bbc.users.repository.AllGroups;
import org.motechproject.ananya.bbc.users.repository.AllUsers;
import org.motechproject.ananya.bbc.users.response.UserResponse;
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
    
    private UserResponse getResponseForUser(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getName());
    }
    
    /*
     * Doesn't do a user existing check. depends upon the DB to throw an exception.
     */
    public UserResponse createUser(String username, String password, String name, List<String> groupNames) {
        List<Group> groupList = new ArrayList<Group>();
        for (String groupName : groupNames) {
            groupList.add(allGroups.findByName(groupName));
        }
        
        User user = new User(username, Utils.getPasswordHash(password), name);
        user.addGroups(groupList);
        allUsers.add(user);
        
        return getResponseForUser(user);
    }

    /*
     * Assumes that the user is present in the database, else will throw a null pointer exception.
     */
    public UserResponse updateUser(int userId, String name) {
        User existingUser = allUsers.findByUserId(userId);

        existingUser.setName(name);
        allUsers.update(existingUser);
        
        return getResponseForUser(existingUser);
    }
    
    public List<UserResponse> getUsers() {
        List<User> userList = allUsers.getAll();
        
        List<UserResponse> userResponseList = new ArrayList<UserResponse>();
        for(User user : userList) {
            userResponseList.add(getResponseForUser(user));
        }
        
        return userResponseList;
    }
}
