package use_case.signup;

public class SignupOutputData {

    private final String username;

    private boolean useCaseFailed;

    private String creationTime;

    public SignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.creationTime = creationTime;
    }

    public String getUsername() {
        return username;
    }
}
