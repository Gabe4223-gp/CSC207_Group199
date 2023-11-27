package use_case.signup;

import entity.User;

/**
 * Interface for sign up a user
 */
public interface SignupDataAccessInterface {
    boolean existsByName(String username);
    void save(User user);
}
