package use_case.select_note;

/**
 * Interface for selecting a TextNote
 */
public interface SelectNoteDataAccessInterface {
    String getSelectedNote(String filename, Integer index, String username);
}
