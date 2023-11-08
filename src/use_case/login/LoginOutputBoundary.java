package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareUsernameFailView(String error);
    void preparePasswordFailView(String error);
}
