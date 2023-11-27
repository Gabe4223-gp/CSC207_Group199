package data_access;

import data_access.API.CreateUserFolderPostAPI;
import entity.User;
import use_case.signup.CreateUserAPIDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

public class SignupUserDAO implements SignupDataAccessInterface, CreateUserAPIDataAccessInterface {
    private final DBConnector dbConnector;
    private final CreateUserFolderPostAPI createUserFolderPostAPI;

    public SignupUserDAO(DBConnector dbConnector, CreateUserFolderPostAPI createUserFolderPostAPI){
        this.dbConnector = dbConnector;
        this.createUserFolderPostAPI = createUserFolderPostAPI;
    }
    @Override
    public boolean existsByName(String username) {
        return this.dbConnector.existsByName(username);
    }
    @Override
    public void save(User user) {
        this.dbConnector.createUser(user.getUsername(), user.getPassword());
    }
    @Override
    public boolean createUserFolder(String username) {
        return this.createUserFolderPostAPI.createUserFolder(username);
    }
}
