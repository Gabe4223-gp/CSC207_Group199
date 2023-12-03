package use_case.login;
/**
 * The data presenter class for login view that send the current data to the login view.
 */
public class LoginOutputData {

    private final String username;
    private boolean useCaseFailed;
    /**
     * Initializes the login output data.
     * @param username is a string containing the users' username.
     * @param useCaseFailed to present whether the login interactor failed or not.
     */
    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }/**
     * Gets the username from the login input data.
     * @return the users' username.
     */
    public String getUsername() {
        return username;
    }
}
