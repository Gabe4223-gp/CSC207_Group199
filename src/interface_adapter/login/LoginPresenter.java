package interface_adapter.login;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          ViewManagerModel viewManagerModel){
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
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
    public void prepareFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChange();
    }

}
