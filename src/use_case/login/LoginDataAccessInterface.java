package use_case.login;

import entity.User;
/**
 * Interface for validating login.
 */
public interface LoginDataAccessInterface {
    /**
     * Check whether the users' username and password valid.
     * @param user contains the users username and password to check if they are registered users.
     * @return whether the user is a valid user or not.
     */
    boolean checkLoginCredentials(User user);
    /**
     * Check whether the user exists in the database.
     * @param user contains the users username and password to check if the user exists.
     * @return whether the user exists or not.
     */
    boolean checkUserExists(User user);
}
