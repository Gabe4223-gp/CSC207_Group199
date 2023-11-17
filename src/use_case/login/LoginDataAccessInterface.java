package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {

    boolean checkLoginCredentials(User user);

    boolean checkUserExists(User user);
}
