package interface_adapter.login;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel,
                          ViewManagerModel viewManagerModel){
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData){
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(loginOutputData.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareUsernameFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(error);
        loginState.setPasswordError(null);
        loginViewModel.firePropertyChange();
    }

    @Override
    public void preparePasswordFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(null);
        loginState.setPasswordError(error);
        loginViewModel.firePropertyChange();
    }

    public void prepareSignupView(){
        SignupViewModel signupViewModel1 = new SignupViewModel();
        SignupState signupState = signupViewModel1.getSignupState();
        this.signupViewModel.setSignupState(signupState);
        this.signupViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

}
