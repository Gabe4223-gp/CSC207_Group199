package use_case.login;

import data_access.LoginUserDAO;

public class LoginInteractor implements LoginInputBoundary {

    final LoginOutputBoundary loginPresenter;
    final LoginDataAccessInterface loginUserDAO;
    public LoginInteractor(LoginUserDAO loginUserDAO,
                           LoginOutputBoundary loginOutputBoundary){
        this.loginUserDAO = loginUserDAO;
        this.loginPresenter = loginOutputBoundary;
    }
    @Override
    public void execute(LoginInputData loginInputData) {
        boolean loginSuccess = this.loginUserDAO.
                checkLoginCredentials(loginInputData.getUsername(), loginInputData.getPassword());
        if(loginSuccess){
            LoginOutputData loginOutputData = new LoginOutputData(loginInputData.getUsername(),false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }else {
            if (this.loginUserDAO.checkUserExists(loginInputData.getUsername())){
                loginPresenter.preparePasswordFailView("Wrong Password");
            }else {
                loginPresenter.prepareUsernameFailView("User does not exist");
            }
        }
    }

    @Override
    public void go_to_signup() {
        loginPresenter.prepareSignupView();
    }
}
