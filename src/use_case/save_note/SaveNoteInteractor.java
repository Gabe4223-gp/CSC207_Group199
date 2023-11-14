package use_case.save_note;

import data_access.SaveNoteDAO;
import entity.Note;
import entity.TextNote;

public class SaveNoteInteractor implements SaveNoteInputBoundary{
    SaveNoteOutputBoundary saveNotePresenter;
    private SaveNoteDAO saveNoteDAO;
    public SaveNoteInteractor(SaveNoteOutputBoundary saveNotePresenter, SaveNoteDAO saveNoteDAO){
        this.saveNotePresenter = saveNotePresenter;
        this.saveNoteDAO = saveNoteDAO;
    }

    @Override
    public void saveFile(SaveNoteInputData saveNoteInputData) {
        TextNote thisNote = new TextNote(saveNoteInputData.getFileName(),
                saveNoteInputData.getCreatedTime(),
                saveNoteInputData.getUsername(), saveNoteInputData.getNoteData());
        //SAVE NOTE DATA to file
        //get all user files
        //create saveNoteOutputData
    }

    @Override
    public void makeFile(SaveNoteInputData saveNoteInputData) {

    }
}
