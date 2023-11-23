package data_access;

import data_access.file_read_write.AllUserFilesDAO;
import entity.TextNote;
import use_case.logged_in.LoggedInDataAccessInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoggedInDAO implements LoggedInDataAccessInterface {
    private final AllUserFilesDAO allUserFilesDAO;
    public LoggedInDAO(AllUserFilesDAO allUserFilesDAO){
        this.allUserFilesDAO = allUserFilesDAO;
    }
    @Override
    public ArrayList<String> getUserFiles(String username) {
        return this.allUserFilesDAO.getAllUserFiles(username);
    }

    @Override
    public TextNote getTextNote(String fileName, String user) {
        String fileData;
        try{
            fileData = allUserFilesDAO.getFileData(user, fileName);
        }catch (Exception e){
            fileData = "";
        }
        return new TextNote(fileName, LocalDateTime.now(), user,fileData);
    }
}
