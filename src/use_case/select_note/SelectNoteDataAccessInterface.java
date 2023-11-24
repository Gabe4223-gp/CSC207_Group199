package use_case.select_note;

import java.util.ArrayList;

/**
 * Interface for selecting a TextNote
 */
public interface SelectNoteDataAccessInterface {
    String getSelectedNote(String filename, Integer index, String username);

    ArrayList<String> getAllFiles(String username);
}
