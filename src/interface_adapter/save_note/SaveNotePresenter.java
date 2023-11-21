package interface_adapter.save_note;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.NoteOutputData;

/**
 * A Presenter Class for note view that is responsible for changing the view after a
 * save, edit or delete action is performed by the User.
 * Implements the SaveNoteOutputBoundary.
 */
public class SaveNotePresenter implements SaveNoteOutputBoundary{

    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    public SaveNotePresenter(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSaveNoteSuccessView(NoteOutputData noteOutputData) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setFilename(noteOutputData.getFileName());
        noteState.setFileTxt(noteOutputData.getFile_txt());
        noteState.setUserFiles(noteOutputData.getUserFiles());
        noteState.setUsername(noteOutputData.getUsername());
        this.noteViewModel.setNoteState(noteState);
        this.noteViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(noteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSaveNoteFailView(String error) {
        // TODO document why this method is empty
    }
}