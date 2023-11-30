package use_case.save_note;

import entity.TextNote;
import entity.TextNoteFactory;
import use_case.NoteOutputData;

import java.util.ArrayList;

/**
 * Save Note Use Case interactor to use the save note data access interface and tell the presenter to prepare success or
 * fail views.
 */
public class SaveNoteInteractor implements SaveNoteInputBoundary{
    private final SaveNoteOutputBoundary saveNotePresenter;
    private final SaveNoteDataAccessInterface saveNoteDAO;
    final UploadFileAPIDataAccessInterface saveAPIDAO;
    public SaveNoteInteractor(SaveNoteOutputBoundary saveNotePresenter, SaveNoteDataAccessInterface saveNoteDAO,
                              UploadFileAPIDataAccessInterface saveAPIDAO){
        this.saveNotePresenter = saveNotePresenter;
        this.saveNoteDAO = saveNoteDAO;
        this.saveAPIDAO = saveAPIDAO;
    }

    /**
     * Save the current file using the data access object based on the input data from the user
     * @param saveNoteInputData The input data from the controller to be saved to the file
     */
    @Override
    public void saveFile(SaveNoteInputData saveNoteInputData) {
        TextNote thisNote = TextNoteFactory.createTextNote(
                saveNoteInputData.getFileName(),
                saveNoteInputData.getCreatedTime(),
                saveNoteInputData.getUsername(),
                saveNoteInputData.getNoteData());
        boolean saveSuccess = this.saveNoteDAO.saveNote(thisNote);
        boolean saveAPISuccess = this.saveAPIDAO.uploadUserFile(saveNoteInputData.getUsername(), thisNote);
        ArrayList<String> files = this.saveNoteDAO.getAllUserFiles(thisNote.getCreatedUser());
        if (saveSuccess){
            NoteOutputData noteOutputData = new NoteOutputData(thisNote.getFileName(),
                    thisNote.getFileTxt(), files, thisNote.getCreatedUser(), false);
            this.saveNotePresenter.prepareSaveNoteSuccessView(noteOutputData);
        }else {
            this.saveNotePresenter.prepareSaveNoteFailView("Save Failed");
        }
    }
}
