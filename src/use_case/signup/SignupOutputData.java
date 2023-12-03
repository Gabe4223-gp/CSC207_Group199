package use_case.signup;

/**
 * The data storage class that saves the username and creation time
 */
public class SignupOutputData {

    private final String username;
    private String creationTime;

    /**
     *Initialize the signup output data with username and creation time
     */
    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

    /**
     *Method used to get private attribute username
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     *Method used to get private attribute creation time
     * @return creation time of the user
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     *Method used to set private attribute creation time
     * @param creationTime the time when the user is created
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
