package interface_adapter.draw_note;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DrawNoteViewModel extends ViewModel {

    public final String TITLE = "Draw Notes";

    public final String FILENAME_LBL = "Filename";

    public final String USER_FILES_LBL = "All files";

    public final String SAVE_BTN_LBL = "Save";

    public final String NEW_BTN_LBL = "New";
    public final String DELETE_BTN_LBL = "Delete";

    private DrawNoteState drawNoteState = new DrawNoteState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DrawNoteViewModel() {super("Draw Note");}

    @Override
    public void firePropertyChange() {
        support.firePropertyChange("state", null, this.drawNoteState);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void setDrawState(DrawNoteState drawNoteState){this.drawNoteState = drawNoteState;}

    public DrawNoteState getDrawState(){
        return drawNoteState;
    }
}
