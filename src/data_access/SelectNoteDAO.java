package data_access;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.FileAccessDAO;
import use_case.select_note.SelectNoteDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class SelectNoteDAO implements SelectNoteDataAccessInterface {

    private final AllUserFilesDAO allUserFilesDAO;

    public SelectNoteDAO(AllUserFilesDAO allUserFilesDAO) {
        this.allUserFilesDAO = allUserFilesDAO;
    }

    @Override
    public String getSelectedNote(String filename, Integer index, String username) {
        ArrayList<String> Data = allUserFilesDAO.getAllUserFiles(username);
        return Data.get(index);
    }
}
