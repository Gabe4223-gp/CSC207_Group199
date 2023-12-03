package use_case.select_note;

import java.util.ArrayList;

/**
 * Data access interface for selecting a TextNote
 */
public interface SelectNoteDataAccessInterface {

    /**
     *Methods used to get selected note in the database
     * by using filename and username
     * @param filename filename of the note
     * @param username username of the user
     */
    String getSelectedNote(String filename, String username);

    /**
     *Methods used to get all files for a single user
     * @param username username of ther user
     */
    ArrayList<String> getAllFiles(String username);
}
