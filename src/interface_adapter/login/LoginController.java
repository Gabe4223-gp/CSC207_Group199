package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LogiInputData(username, password);
        loginInteractor.execute(loginInputData);
    }
}