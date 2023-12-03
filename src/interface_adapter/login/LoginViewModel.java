package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * A View Model Class that is responsible handling and managing the logic displayed by the login view.
 * Login View Model extends the View Model.
 */
public class LoginViewModel extends ViewModel {

    public static final String TITLE = "Log in";

    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";


    private LoginState loginState =new LoginState();

    /**
     * Initializer for the login view model. Subclass of the login view.
     */
    public LoginViewModel() {
        super("log in");
    }
    /**
     * Method sets the current login state.
     * @param loginState contains the getters and setters for login username and login password.
     */
    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }
    /**
     * Method gets the current login state.
     * @return the login state.
     */
    public LoginState getLoginState() {
        return loginState;
    }

    /**
     * Manages a list of listeners and dispatches PropertyChangeEvents to them.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event to login listeners to
     * track updates of properties.
     */
    @Override
    public void firePropertyChange() {
        support.firePropertyChange("state", null, this.loginState);
    }
    /**
     * Called when there is a change to a listener property.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}