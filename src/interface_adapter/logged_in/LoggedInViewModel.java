package interface_adapter.logged_in;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel{

    private static final String TITLE_LABEL = "Select your note";

    private static final String TEXT_NOTE_LABEL = "Text Note";

    private static final String DRAW_NOTE_LABEL = "Draw Note";

    private  LoggedInState loggedInState = new LoggedInState();

    public LoggedInViewModel() {
        super(("Select your note"));
    }

    public void setState(LoggedInState loggedInState){
        this.loggedInState = loggedInState;
    }

    public LoggedInState getState(){
        return loggedInState;
    }

    private  final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChange() {
        support.firePropertyChange("state",null, this.loggedInState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}