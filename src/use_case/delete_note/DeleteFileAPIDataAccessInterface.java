package use_case.delete_note;

public interface DeleteFileAPIDataAccessInterface {
    boolean deleteUserFile(String username, String filename);
}
