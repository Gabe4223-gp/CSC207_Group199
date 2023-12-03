package use_case.login;
/**
 * Interface for handling input data from the user, used by the login interactor to invoke the DAOs and
 * login presenter.
 */
public interface LoginInputBoundary {
    /**
     * Login the user using the data access object based on the input data from the user
     * @param loginInputData The input data from the controller to login the user
     */
    void execute(LoginInputData loginInputData);
    /**
     * Update the view to signup view.
     */
    void go_to_signup();
}
