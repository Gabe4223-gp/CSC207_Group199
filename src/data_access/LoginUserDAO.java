package data_access;

import use_case.login.LoginDataAccessInterface;

public class LoginUserDAO implements LoginDataAccessInterface {
    private final DBConnector dbConnector;
    public LoginUserDAO (DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public boolean checkLoginCredentials(String username, String password){
        return this.dbConnector.checkLoginCredentials(username,password);
    }

    @Override
    public boolean checkUserExists(String username){
        return this.dbConnector.getUserObj(username) != null;
    }
}
