package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Presenter Class for signup view that is responsible for changing the view after the user
 * clicks the signup button.
 * Implements the SignupOutputBoundary.
 */

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

    /**
     *Method used to prepare successful view when someone creates a user
     * and automatically switch to login view
     * @param signupOutputData the output data, usernmae and creation time
     */
    @Override
    public void prepareSuccessView(SignupOutputData signupOutputData) {
        LocalDateTime responseTime = LocalDateTime.parse(signupOutputData.getCreationTime());
        signupOutputData.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginViewModel loginViewModel1 = new LoginViewModel();
        LoginState loginState = loginViewModel1.getLoginState();
        loginState.setUsername(signupOutputData.getUsername());
        this.loginViewModel.setLoginState(loginState);
        loginViewModel.firePropertyChange();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    /**
     *Method used to prepare failed view when someone fail to create a user
     * and signup view is fired to show message
     * @param error the string that describes where is wrong
     */
    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getSignupState();
        signupState.setError(error);
        signupViewModel.firePropertyChange();

    }

}

