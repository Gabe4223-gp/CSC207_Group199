package interface_adapter.note;
import interface_adapter.ViewManagerModel;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.save_note.SaveNoteOutputData;

public class NotePresenter implements SaveNoteOutputBoundary{

    private final NoteViewModel noteViewModel;
    private ViewManagerModel viewManagerModel;
    public NotePresenter(NoteViewModel noteViewModel, ViewManagerModel, viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SaveNoteOutputData saveNoteOutputData) {
        NoteState noteState =
    }
}