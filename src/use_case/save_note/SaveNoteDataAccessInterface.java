package use_case.save_note;

import entity.TextNote;

import java.util.ArrayList;


/**
 * Interface for saving a TextNote
 */
public interface SaveNoteDataAccessInterface {

    /**
     * Method to save a given textNote.
     * @param textNote The TextNote object to
     * @return Returns True if a given TextNote is saved succesfully
     */
    boolean saveNote(TextNote textNote);
    boolean uploadUserFile(String username, TextNote textNote);
    ArrayList<String> getAllUserFiles(String username);
}
