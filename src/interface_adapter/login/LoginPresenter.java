package interface_adapter.login;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public LoginPresenter(LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData){
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(loginOutputData.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChange();
    }


    @Override
    public void prepareFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChange();
    }

}
