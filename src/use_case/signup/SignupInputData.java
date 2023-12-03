package use_case.signup;

/**
 * The data storage class for signup view that saves the username, password, repeated password from
 * signup view
 */
public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    /**
     * Initialize the signup input data with username, password and repeat password
     */
    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Method used to get private attribute username
     * @return username of the user
     */
    String getUsername() {
        return username;
    }

    /**
     *Method used to get private attribute password
     * @return password of the user
     */
    String getPassword() {
        return password;
    }

    /**
     *Method used to get private attribute repeated password
     * @return repeat password of the user
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
