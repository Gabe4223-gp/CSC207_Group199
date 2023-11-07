package interface_adapter.note;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoteViewModel extends ViewModel {

    public String TITLE = "Notes";
    public final String FILENAME_LBL = "Filename";
    public final String USER_FILES_LBL = "All files";
    public final String SAVE_BTN_LBL = "Save";
    public final String NEW_BTN_LBL = "New";
    private NoteState noteState = new NoteState();
    public NoteViewModel() {
        super("notes");
    }

    public void setNoteState(NoteState noteState){
        this.noteState = noteState;
    }

    public NoteState getNoteState() {
        return noteState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChange() {
        support.firePropertyChange("state", null, this.noteState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
