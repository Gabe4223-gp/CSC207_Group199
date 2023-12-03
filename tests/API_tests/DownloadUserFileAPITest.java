package API_tests;

import data_access.API.*;
import entity.TextNote;
import entity.TextNoteFactory;
import entity.User;
import entity.UserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DownloadUserFileAPITest {
    private DownloadUserFileGetAPI downloadUserFileGetAPI;
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    private UploadUserFilePostAPI uploadUserFilePostAPI;
    private DeleteDataPostAPI deleteDataPostAPI;

    @Before
    public void init() {
        downloadUserFileGetAPI = APIFactory.downloadFilesAPI();
        createUserFolderPostAPI = APIFactory.createUserAPI();
        uploadUserFilePostAPI = APIFactory.uploadAPI();
        deleteDataPostAPI = APIFactory.deleteAPI();
    }
    @Test
    public void testDownloadUserFileFail(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        ArrayList<String> downloadData = downloadUserFileGetAPI.downloadUserFile(newUser1.getUsername());
        assertEquals(downloadData, new ArrayList<>());
    }
    @Test
    public void testDownloadUserFilePass(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        TextNote textNote = TextNoteFactory.createTextNote("testFile1", LocalDateTime.now(),
                newUser1.getUsername(), "testData1");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        textNote = TextNoteFactory.createTextNote("testFile2", LocalDateTime.now(),
                newUser1.getUsername(), "testData2");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        textNote = TextNoteFactory.createTextNote("testFile3", LocalDateTime.now(),
                newUser1.getUsername(), "testData3");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        ArrayList<String> fileData = new ArrayList<>(Arrays.asList("testData1", "testData2", "testData3"));
        assertEquals(fileData, downloadUserFileGetAPI.downloadUserFile(newUser1.getUsername()));
    }
    @After
    public void deleteTestUsers(){
        deleteDataPostAPI.deleteUser("qwerty");
    }
}
