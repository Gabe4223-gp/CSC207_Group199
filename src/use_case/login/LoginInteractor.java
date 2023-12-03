package use_case.login;

import data_access.LoginUserDAO;
import entity.User;
import entity.UserFactory;
/**
 * Login Use Case interactor to use the login data access interface and tell the presenter to prepare success or
 * fail views.
 */
public class LoginInteractor implements LoginInputBoundary {

    final LoginOutputBoundary loginPresenter;
    final LoginDataAccessInterface loginUserDAO;
    /**
     * Initializes the login interactor
     * @param loginUserDAO connects the login interactor to the data access objects and database to execute login data.
     * @param loginOutputBoundary to send to the presenter after execution.
     */
    public LoginInteractor(LoginUserDAO loginUserDAO,
                           LoginOutputBoundary loginOutputBoundary){
        this.loginUserDAO = loginUserDAO;
        this.loginPresenter = loginOutputBoundary;
    }
    /**
     * Login the user using the data access object based on the input data from the user
     * @param loginInputData The input data from the controller to login the user
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        User thisUser = UserFactory.createUser(loginInputData.getUsername(), loginInputData.getPassword());
        boolean loginSuccess = this.loginUserDAO.
                checkLoginCredentials(thisUser);
        if(loginSuccess){
            LoginOutputData loginOutputData = new LoginOutputData(thisUser.getUsername(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }else {
            if (this.loginUserDAO.checkUserExists(thisUser)){
                loginPresenter.preparePasswordFailView("Wrong Password");
            }else {
                loginPresenter.prepareUsernameFailView("User does not exist");
            }
        }
    }
    /**
     * Update the view to signup view.
     */
    @Override
    public void go_to_signup() {
        loginPresenter.prepareSignupView();
    }
}
