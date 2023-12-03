package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 A class stores the state of view and can be updated by presenter to
 *change the view
 */
public class SignupViewModel extends ViewModel {

    public static final String TITLE = "Sign up";

    public static final String USERNAME_LABEL = "Create Username";

    public static final  String PASSWORD_LABEL = "Create Password";

    public static final String REPEAT_LABEL = "Repeat Password";

    public static final String LOGIN_BUTTON_LABEL = "Back to log in";

    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";

    private SignupState signupState = new SignupState();
    public SignupViewModel() {
        super("sign up");
    }

    public void setSignupState(SignupState signupState){
        this.signupState = signupState;
    }

    public SignupState getSignupState(){
        return signupState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChange() {
        support.firePropertyChange("state", null, this.signupState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}