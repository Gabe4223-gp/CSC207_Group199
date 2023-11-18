package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;


public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        SignupState signupState = signupViewModel.getSignupState();
        signupState.setSuccessMessage(message);
        signupViewModel.firePropertyChange();

    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getSignupState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChange();

    }

    @Override
    public void prepareLoginView() {
        LoginViewModel loginViewModel1 = new LoginViewModel();
        LoginState loginState = loginViewModel1.getLoginState();
        this.loginViewModel.setLoginState(loginState);
        this.loginViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

