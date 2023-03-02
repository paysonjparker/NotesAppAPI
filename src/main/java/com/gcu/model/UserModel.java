package com.gcu.model;

/**
 * User Model class.
 */
public class UserModel {

    /**
     * User ID number.
     */
    private int id;
    /**
     * User full name.
     */
    private String full_name;
    /**
     * User email address.
     */
    private String email;
    /**
     * User password.
     */
    private String password;

    /**
     * Constructs a new User Model.
     * @param id User ID number.
     * @param full_name User full name.
     * @param email User email address.
     * @param password User password.
     */
    public UserModel(int id, String full_name, String email, String password) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs default User Model.
     */
    public UserModel() {

    }

    /**
     * Gets User ID number.
     * @return User ID number.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new User ID number.
     * @param id New User ID number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets User full name.
     * @return User full name.
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * Sets new User full name.
     * @param full_name New User full name.
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     * Gets User email address.
     * @return User email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets new User email address.
     * @param email New User email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets User password.
     * @return User password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets new User password.
     * @param password New User password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
