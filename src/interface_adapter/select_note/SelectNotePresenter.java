package interface_adapter.select_note;

import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import use_case.NoteOutputData;
import use_case.select_note.SelectNoteOutputBoundary;

/**
 * A Presenter Class for note view that is responsible for changing the view after a
 * save, edit, select or delete action is performed by the User.
 * Implements the SelectNoteOutputBoundary.
 */
public class SelectNotePresenter implements SelectNoteOutputBoundary {

    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    public SelectNotePresenter(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     *A method used to prepare failed view by showing error message
     * on the vire
     * @param error a string message shows where is wrong in the process
     */
    @Override
    public void prepareSelectFailView(String error) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setError(error);
        noteViewModel.firePropertyChange();
    }

    /**
     *A method used to prepare successful view by showing selected filename
     * and selected text and set error to empty
     * @param noteOutputData output data with filename and text
     */
    @Override
    public void prepareSelectSuccessfulView(NoteOutputData noteOutputData) {
        NoteState noteState = noteViewModel.getNoteState();
        noteState.setFilename(noteOutputData.getFileName());
        noteState.setFileTxt(noteOutputData.getFile_txt());
        noteState.setError("");
        this.noteViewModel.setNoteState(noteState);
        this.noteViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(noteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
