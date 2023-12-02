package entity;

public class UserFactory {
    private UserFactory(){}

    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}
