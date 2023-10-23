package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {

    boolean existsByUsername(String username);

    boolean matchesByPassword(String username, String password);

    User getUser(String username);
}
