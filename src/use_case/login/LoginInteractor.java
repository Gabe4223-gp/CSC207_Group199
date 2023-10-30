package use_case.login;

import data_access.LoginUserDAO;

public class LoginInteractor implements LoginInputBoundary {

    final LoginOutputBoundary loginPresenter;
    public LoginInteractor(LoginUserDAO loginUserDAO,
                           LoginOutputBoundary loginOutputBoundary){

        this.loginPresenter = loginOutputBoundary;
    }
    @Override
    public void execute(LoginInputData loginInputData) {
        LoginOutputData loginOutputData = new LoginOutputData(loginInputData.getUsername(),false);
        loginPresenter.prepareSuccessView(loginOutputData);
    }
}
