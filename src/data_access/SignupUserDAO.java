package data_access;

import entity.User;
import use_case.signup.SignupDataAccessInterface;

public class SignupUserDAO implements SignupDataAccessInterface {
    private final DBConnector dbConnector;

    public SignupUserDAO(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }
    @Override
    public boolean existsByName(String username) {
        return this.dbConnector.existsByName(username);
    }

    @Override
    public void save(User user) {
        this.dbConnector.createUser(user.getUsername(), user.getPassword());
    }
}
