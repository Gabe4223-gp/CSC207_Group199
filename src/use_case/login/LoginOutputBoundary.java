package use_case.login;
/**
 * Interface for handling output data from the user, used by the login presenter to send data and update the login view.
 */
public interface LoginOutputBoundary {
    /**
     * Method prepares a success view to send to the user in the case that login is successful.
     * @param user is the data passed by the login interactor that contains the user username
     *                        to be used in the loggedInState.
     */
    void prepareSuccessView(LoginOutputData user);
    /**
     * Method prepares a fail view from a username conflict.
     * @param error is a string used to change the login state and update the login view.
     */
    void prepareUsernameFailView(String error);
    /**
     * Method prepares a fail view from a password conflict.
     * @param error is a string used to change the login state and update the login view.
     */
    void preparePasswordFailView(String error);
    /**
     * Method prepares a signup view for the user.
     */
    void prepareSignupView();
}
