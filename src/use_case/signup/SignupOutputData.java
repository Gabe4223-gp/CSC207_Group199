package use_case.signup;

/**
 * The data storage class that saves the username and creation time
 */
public class SignupOutputData {

    private final String username;
    private String creationTime;

    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
