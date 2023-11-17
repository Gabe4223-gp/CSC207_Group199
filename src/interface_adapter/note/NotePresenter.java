package interface_adapter.note;
import interface_adapter.ViewManagerModel;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.save_note.SaveNoteOutputData;

/**
 * A Presenter Class for note view that is responsible for changing the view after a
 * save, edit or delete action is performed by the User.
 * Implements the SaveNoteOutputBoundary.
 */
public class NotePresenter implements SaveNoteOutputBoundary{

    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    public NotePresenter(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSaveNoteSuccessView(SaveNoteOutputData saveNoteOutputData) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setFilename(saveNoteOutputData.getFileName());
        noteState.setFile_txt(saveNoteOutputData.getFile_txt());
        noteState.setUserFiles(saveNoteOutputData.getUserFiles());
        noteState.setUsername(saveNoteOutputData.getUsername());
        this.noteViewModel.setNoteState(noteState);
        this.noteViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(noteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSaveNoteFailView(String error) {

    }
}