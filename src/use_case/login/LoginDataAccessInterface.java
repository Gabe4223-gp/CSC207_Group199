package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {

    boolean checkLoginCredentials(String username, String password);

    boolean checkUserExists(String username);
}
