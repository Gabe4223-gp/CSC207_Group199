package data_access;

import entity.User;
import use_case.signup.SignupDataAccessInterface;

public class SignupUserDAO implements SignupDataAccessInterface {
    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void save(User user) {

    }
}
