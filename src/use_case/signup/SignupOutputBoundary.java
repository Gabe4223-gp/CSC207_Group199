package use_case.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(String message);

    void prepareFailView(String error);

    void prepareLoginView();
}
