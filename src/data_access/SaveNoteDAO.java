package data_access;

import data_access.API.UploadUserFilePostAPI;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import use_case.save_note.SaveNoteDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Class to write the given TextNote to a file
 */
public class SaveNoteDAO implements SaveNoteDataAccessInterface {

    private final TextNoteWriterDAO textNoteWriterDAO;
    private final AllUserFilesDAO allUserFilesDAO;
    private final UploadUserFilePostAPI uploadUserFilePostAPI;

    public SaveNoteDAO(TextNoteWriterDAO textNoteWriterDAO, AllUserFilesDAO allUserFilesDAO, UploadUserFilePostAPI uploadUserFilePostAPI){
        this.textNoteWriterDAO = textNoteWriterDAO;
        this.allUserFilesDAO = allUserFilesDAO;
        this.uploadUserFilePostAPI = uploadUserFilePostAPI;
    }
    @Override
    public boolean saveNote(TextNote textNote) {
        try
        {
            this.textNoteWriterDAO.writeDataToFile(textNote.getFileName(),
                    textNote.getCreatedUser(),
                    textNote.getFileTxt());
            return true;
        }catch (IOException e){
            return false;
        }
    }

    @Override
    public ArrayList<String> getAllUserFiles(String username) {
        return allUserFilesDAO.getAllUserFiles(username);
    }

    @Override
    public boolean uploadUserFile(String username, TextNote textNote) {
        return this.uploadUserFilePostAPI.uploadUserFile(username, textNote);
    }
}
