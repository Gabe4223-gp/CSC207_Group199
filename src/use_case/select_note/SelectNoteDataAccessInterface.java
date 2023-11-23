package use_case.select_note;

public interface SelectNoteDataAccessInterface {
    String getSelectedNote(String filename, Integer index, String username);
}
