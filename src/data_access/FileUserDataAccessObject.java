package data_access;

import entity.User;
import use_case.login.LoginDataAccessInterface;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements LoginDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    public void fileUserDataAccessObject(){
        // not yet implemented
    }


    @Override
    public boolean existsByUsername(String username) {
        return false; // not yet implemented
    }

    @Override
    public boolean matchesByPassword(String username, String password){
        return false; // not yet implemented
    }

    @Override
    public User getUser(String username) {
        return null; // not yet implemented
    }
}
