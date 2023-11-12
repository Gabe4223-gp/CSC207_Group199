package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;


public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void prepareLoginView() {
    LoginState loginState = loginViewModel.getLoginState();
    this.loginViewModel.setLoginState(loginState);
    this.loginViewModel.firePropertyChange();

    this.viewManagerModel.setActiveView(loginViewModel.getViewName());
    this.viewManagerModel.firePropertyChanged();
    }
}

