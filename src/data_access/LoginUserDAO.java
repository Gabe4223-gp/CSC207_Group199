package data_access;

import entity.User;
import use_case.login.LoginDataAccessInterface;
/**
 * Class to verify if a user is an existing or valid user and grant them access to the app.
 */
public class LoginUserDAO implements LoginDataAccessInterface {
    /**
     * dbConnector connects to a MySQL database containing all created users.
     */
    private final DBConnector dbConnector;
    /**
     * Initializes the login data access object dbConnector.
     * @param dbConnector to connect to a MySQL database containing all created users.
     */
    public LoginUserDAO (DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
    /**
     * Check whether the users' username and password valid.
     * @param user contains the users username and password to check if they are registered users.
     * @return whether the user is a valid user or not.
     */
    @Override
    public boolean checkLoginCredentials(User user){
        return this.dbConnector.checkLoginCredentials(user.getUsername(), user.getPassword());
    }
    /**
     * Check whether the user exists in the database.
     * @param user contains the users username and password to check if the user exists.
     * @return whether the user exists or not.
     */
    @Override
    public boolean checkUserExists(User user){
        return this.dbConnector.getUserObj(user.getUsername()) != null;
    }
}
