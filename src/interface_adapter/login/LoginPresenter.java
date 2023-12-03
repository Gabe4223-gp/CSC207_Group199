package interface_adapter.login;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
/**
 * A Presenter Class for login view that is responsible for changing the view after a
 * login or signup action is performed by the User.
 * Implements the LoginOutputBoundary.
 */
public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;
    /**
     * Initializes the login presenter class.
     * @param loggedInViewModel presents the data displayed on the loggedInView in the case that the view is changed
     *                          to logged in.
     * @param loginViewModel presents the data displayed on the loginView in the case that info in the login view
     *                       needs to be presented to the user.
     * @param signupViewModel presents the data displayed on the signupView in the case that the view is changed
     *                       to signup.
     * @param viewManagerModel updates/changes the current active view.
     */
    public LoginPresenter(LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel,
                          ViewManagerModel viewManagerModel){
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }
    /**
     * Method prepares a success view to send to the user in the case that login is successful.
     * @param loginOutputData is the data passed by the login interactor that contains the user username
     *                        to be used in the loggedInState.
     */
    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData){
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(loginOutputData.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    /**
     * Method prepares a fail view from a username conflict.
     * @param error is a string used to change the login state and update the login view.
     */
    @Override
    public void prepareUsernameFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(error);
        loginState.setPasswordError(null);
        loginViewModel.firePropertyChange();
    }
    /**
     * Method prepares a fail view from a password conflict.
     * @param error is a string used to change the login state and update the login view.
     */
    @Override
    public void preparePasswordFailView(String error){
        LoginState loginState = loginViewModel.getLoginState();
        loginState.setUsernameError(null);
        loginState.setPasswordError(error);
        loginViewModel.firePropertyChange();
    }
    /**
     * Method prepares a signup view for the user.
     */
    public void prepareSignupView(){
        SignupViewModel signupViewModel1 = new SignupViewModel();
        SignupState signupState = signupViewModel1.getSignupState();
        this.signupViewModel.setSignupState(signupState);
        this.signupViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

}
