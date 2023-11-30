package data_access;

import data_access.API.DownloadUserFileGetAPI;
import data_access.API.ListContentsUserFolderPostAPI;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import use_case.logged_in.LoggedInDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoggedInDAO implements LoggedInDataAccessInterface {
    private final AllUserFilesDAO allUserFilesDAO;
    private final TextNoteWriterDAO textNoteWriterDAO;
    private final DownloadUserFileGetAPI downloadUserFileGetAPI;
    private final ListContentsUserFolderPostAPI listContentsUserFolderPostAPI;
    public LoggedInDAO(AllUserFilesDAO allUserFilesDAO, TextNoteWriterDAO textNoteWriterDAO,
                       DownloadUserFileGetAPI downloadUserFileGetAPI, ListContentsUserFolderPostAPI listContentsUserFolderPostAPI){
        this.allUserFilesDAO = allUserFilesDAO;
        this.textNoteWriterDAO = textNoteWriterDAO;
        this.downloadUserFileGetAPI = downloadUserFileGetAPI;
        this.listContentsUserFolderPostAPI = listContentsUserFolderPostAPI;
    }
    @Override
    public ArrayList<String> getUserFiles(String username) {
        return this.allUserFilesDAO.getAllUserFiles(username);
    }
    @Override
    public void writeDataToFile(String filename, String username, String filedata) throws IOException {
        this.textNoteWriterDAO.writeDataToFile(filename, username, filedata);
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
    @Override
    public ArrayList<String> downloadUserFile(String username) {
        return this.downloadUserFileGetAPI.downloadUserFile(username);
    }
    @Override
    public ArrayList<String> listContentsUserFolder(String username) {
        return this.listContentsUserFolderPostAPI.listContentsUserFolder(username);
    }
}
