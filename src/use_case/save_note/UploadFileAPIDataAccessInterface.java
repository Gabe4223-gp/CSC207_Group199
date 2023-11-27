package use_case.save_note;

import entity.TextNote;

public interface UploadFileAPIDataAccessInterface {
    boolean uploadUserFile(String username, TextNote textNote);
}
