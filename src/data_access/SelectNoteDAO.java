package data_access;
import data_access.file_read_write.AllUserFilesDAO;
import use_case.select_note.SelectNoteDataAccessInterface;

import java.util.ArrayList;

public class SelectNoteDAO implements SelectNoteDataAccessInterface {

    private final AllUserFilesDAO allUserFilesDAO;

    public SelectNoteDAO(AllUserFilesDAO allUserFilesDAO) {
        this.allUserFilesDAO = allUserFilesDAO;
    }

    @Override
    public String getSelectedNote(String filename, String username) {
        return allUserFilesDAO.getFileData(username,filename);
    }

    @Override
    public ArrayList<String> getAllFiles(String username) {
        return allUserFilesDAO.getAllUserFiles(username);
    }
}
