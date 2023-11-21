package data_access;

import data_access.file_read_write.AllUserFilesDAO;
import entity.TextNote;
import use_case.delete_note.DeleteNoteDataAccessInterface;
import use_case.delete_note.DeleteNoteInputData;

import javax.imageio.IIOException;
import java.util.ArrayList;

public class DeleteNoteDAO implements DeleteNoteDataAccessInterface {

    private AllUserFilesDAO allUserFilesDAO;
    public DeleteNoteDAO(AllUserFilesDAO allUserFilesDAO){
        this.allUserFilesDAO = allUserFilesDAO;
    }
    @Override
    public boolean deleteNote(String username, String filename) {
        return false;
    }
    public ArrayList<String> getUserFiles(String username){
        return this.allUserFilesDAO.getAllUserFiles(username);
    }

    @Override
    public String getFileData(String username, String filename) {
        return this.allUserFilesDAO.getFileData(username, filename);
    }
}
