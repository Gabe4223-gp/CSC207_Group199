package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel{

    private  LoggedInState loggedInState = new LoggedInState();

    public static final String TITLE_LABEL = "Select your note";
    public static final String TEXT_NOTE_LABEL = "Text Note";
    public static final String DRAW_NOTE_LABEL = "Draw Note";

    public LoggedInViewModel() {
        super(("logged in"));
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