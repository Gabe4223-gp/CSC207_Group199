package use_case.delete_note;

import java.util.ArrayList;

public interface DeleteNoteDataAccessInterface {
    boolean deleteNote(String username, String filename);
    ArrayList<String> getUserFiles(String username);
    String getFileData(String username, String filename);
}
