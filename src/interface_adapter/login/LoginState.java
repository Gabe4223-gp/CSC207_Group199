package interface_adapter.login;
/**
 * A State Class for login view containing getters and setters for username and password input
 * given by the user.
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    /**
     * Initializes the login state class.
     * @param copy copies the data from the getter and setter methods.
     */
    public LoginState(LoginState copy){
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
    }
    /**
     * empty constructor that instantiates attributes with the above default values
     */
    public LoginState(){}
    /**
     * Method gives access to the user username for all login purposes.
     * @return the users' username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Method sets the current value of the users' username to the param when called.
     * @param username is a string containing the users' username.
     */
    public void setUsername(String username){
        this.username = username;
    }
    /**
     * Method gives access to the user password for all login purposes.
     * @return the users' password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Method sets the current value of the users' password to the param when called.
     * @param password is a string containing the users' password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Method gives a user password error for all login purposes.
     * @return a user password error.
     */
    public String getPasswordError() {
        return passwordError;
    }
    /**
     * Method sets the current value of the users' password to null in the case of a password error.
     * @param passwordError is a string containing the users' password.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
    /**
     * Method gives a user username error for all login purposes.
     * @return a user username error.
     */
    public String getUsernameError() {
        return usernameError;
    }
    /**
     * Method sets the current value of the users' username to null in the case of a username error.
     * @param usernameError is a string containing the users' password.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
}
