package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;
/**
 * A Controller Class that is responsible for taking in the username and password input data
 * from the login state using the login view and invoking the login interactor.
 */
public class LoginController {

    final LoginInputBoundary loginInteractor;
    /**
     * Initializes LoginController given input from the LoginInputBoundary to invoke the login interactor.
     * @param loginInteractor to relay input data to the login interactor.
     */
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }
    /**
     * Takes the input username and password to the login interactor to implement the login use case.
     * @param username is a string containing the username of the user to pass on to the login interactor.
     * @param password is a string containing the password of the user to pass on to the login interactor.
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }
    /**
     * Invokes the login interactor to return the view to signup.
     */
    public void signupView(){
        loginInteractor.go_to_signup();
    }
}
