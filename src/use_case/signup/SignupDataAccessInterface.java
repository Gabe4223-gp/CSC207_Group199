package use_case.signup;

import entity.User;

/**
 * Data access interface for sign up a user
 */
public interface SignupDataAccessInterface {
    /**
     *Method used to create a user folder and
     * @param username the username of the user
     * true if successfully create a folder for the user
     */
    boolean createUserFolder(String username);
    /**
     *Method used to check whether the username already exists in the database
     * @param username the username of the user
     * true if the username already exists in the database
     */
    boolean existsByName(String username);
    /**
     *Method used to save the user in the database
     * @param user the user with information of username and password
     */
    void save(User user);
}
