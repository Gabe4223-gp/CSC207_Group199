package app;

import data_access.LoginUserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory(){}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginController loginController = createLoginUseCase(
                viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                loginUserDAO
        );
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(
                loggedInViewModel, loginViewModel,viewManagerModel
        );
        LoginInputBoundary loginInteractor = new LoginInteractor(
                loginUserDAO, loginOutputBoundary
        );
        return new LoginController(loginInteractor);
    }
}
