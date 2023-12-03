package use_case.login;
/**
 * The data storage class for login view that saves the current data from the login view.
 */
public class LoginInputData {
    private final String username;
    private final String password;
    /**
     * Initializes the login input data.
     * @param username takes the username from user input.
     * @param password takes the password from user input.
     */
    public LoginInputData (String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Method gets the user username.
     * @return the username of user.
     */
    String getUsername() {
        return username;
    }
    /**
     * Method gets the user password.
     * @return the password of user.
     */
    String getPassword() {
        return password;
    }
}
