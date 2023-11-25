package data_access;

import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.DeleteNoteWriterDAO;
import use_case.delete_note.DeleteNoteDataAccessInterface;

import java.util.ArrayList;

public class DeleteNoteDAO implements DeleteNoteDataAccessInterface {

    private AllUserFilesDAO allUserFilesDAO;
    private DeleteNoteWriterDAO deleteNoteWriterDAO;
    public DeleteNoteDAO(AllUserFilesDAO allUserFilesDAO, DeleteNoteWriterDAO deleteNoteWriterDAO){
        this.allUserFilesDAO = allUserFilesDAO;
        this.deleteNoteWriterDAO = deleteNoteWriterDAO;
    }
    @Override
    public boolean deleteNote(String username, String filename) {
        return this.deleteNoteWriterDAO.deleteNoteFile(username, filename);
    }
    public ArrayList<String> getUserFiles(String username){
        return this.allUserFilesDAO.getAllUserFiles(username);
    }

    @Override
    public String getFileData(String username, String filename) {
        return this.allUserFilesDAO.getFileData(username, filename);
    }
}
