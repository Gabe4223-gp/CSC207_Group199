package interface_adapter.note;
import interface_adapter.ViewManagerModel;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.save_note.SaveNoteOutputData;

public class NotePresenter implements SaveNoteOutputBoundary{

    private final NoteViewModel noteViewModel;
    private ViewManagerModel viewManagerModel;
    public NotePresenter(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SaveNoteOutputData saveNoteOutputData) {
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
    public void prepareFailView(String error) {

    }
}