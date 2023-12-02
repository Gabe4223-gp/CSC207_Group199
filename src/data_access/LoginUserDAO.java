package data_access;

import entity.User;
import use_case.login.LoginDataAccessInterface;

public class LoginUserDAO implements LoginDataAccessInterface {
    private final DBConnector dbConnector;
    public LoginUserDAO (DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public boolean checkLoginCredentials(User user){
        return this.dbConnector.checkLoginCredentials(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean checkUserExists(User user){
        return this.dbConnector.getUserObj(user.getUsername()) != null;
    }
}
