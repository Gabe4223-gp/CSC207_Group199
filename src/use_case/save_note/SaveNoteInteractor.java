package use_case.save_note;

import data_access.SaveNoteDAO;
import entity.Note;
import entity.TextNote;

import java.util.ArrayList;

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
        ArrayList<String> files = new ArrayList<>();
        SaveNoteOutputData saveNoteOutputData = new SaveNoteOutputData(thisNote.getFileName(),
                thisNote.getFileTxt(), files, thisNote.getCreatedUser(), false);


    }
}
