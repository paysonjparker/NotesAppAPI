package com.gcu.business;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusinessService {

    @Autowired
    DataAccessInterface<UserModel> userDataService;

    /**
     * Gets a list of all users.
     * @return List of users.
     */
    public List<UserModel> getUsers()
    {
        return userDataService.findAll();
    }

    /**
     * Gets a specific user by ID number.
     * @param id ID being searched for.
     * @return The user assocaited with that ID number.
     */
    public UserModel getUserById(int id)
    {
        return userDataService.findById(id);
    }

    /**
     * Adds a user to the database.
     * @param user User being added.
     */
    public void addUser(UserModel user)
    {
        userDataService.create(user);
    }

    /**
     * Updates an existing user information.
     * @param user User being updated.
     */
    public void update(UserModel user)
    {
        userDataService.update(user);
    }

    /**
     * Deletes a user.
     * @param user User being deleted.
     */
    public void delete(UserModel user)
    {
        userDataService.delete(user);
    }
}
