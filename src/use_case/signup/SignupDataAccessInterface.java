package use_case.signup;

import entity.User;

public interface SignupDataAccessInterface {
    boolean existsByName(String username);

    void save(User user);
}
