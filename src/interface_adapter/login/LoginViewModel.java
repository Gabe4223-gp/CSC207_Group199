package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LoginViewModel extends ViewModel {

    public final String TITLE = "Log in";

    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public static final String login_btn_lbl = "Log in";

    private LoginState loginState =new LoginState();


    public LoginViewModel() {
        super("Log In");
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    @Override
    public void firePropertyChange() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
