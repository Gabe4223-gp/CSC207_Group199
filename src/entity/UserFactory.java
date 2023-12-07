package entity;
/**
 * Class using factory design pattern to fabricate users, allowing use-cases and
 * tests to create a new user.
 */
public class UserFactory {
    /**
     * Initializes UserFactory factory class.
     */
    private UserFactory(){}

    /**
     * Method creates a new user.
     * @return a new User.
     */
    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}
