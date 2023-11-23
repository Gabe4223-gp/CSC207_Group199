package interface_adapter.select_note;

import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import use_case.select_note.SelectNoteOutputBoundary;
import use_case.select_note.SelectNoteOutputData;


public class SelectNotePresenter implements SelectNoteOutputBoundary {

    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    public SelectNotePresenter(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSelectFailView(String error) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setError(error);
        noteViewModel.firePropertyChange();
    }

    @Override
    public void prepareSelectSuccessfulView(SelectNoteOutputData selectNoteOutputData) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setFilename(selectNoteOutputData.getFilename());
        noteState.setFileTxt(selectNoteOutputData.getNoteData());
        this.noteViewModel.setNoteState(noteState);
        this.noteViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(noteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
