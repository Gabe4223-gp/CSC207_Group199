package interface_adapter.draw_note;

import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;

import java.beans.PropertyChangeListener;

public class DrawNoteViewModel extends ViewModel {

    public final String TITLE_LABEL = "Draw Note";

    public final String SAVE_LABEL = "Save";

    private DrawNoteState drawNoteState;

    public DrawNoteViewModel() {super("Draw Note");}

    @Override
    public void firePropertyChange() {

    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public void setState(DrawNoteState drawNoteState){this.drawNoteState = drawNoteState;}

    public DrawNoteState getState(){
        return drawNoteState;
    }
}
