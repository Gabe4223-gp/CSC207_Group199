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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ListContentsUserFolderTest {
    private ListContentsUserFolderPostAPI listContentsUserFolderPostAPI;
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    private DeleteDataPostAPI deleteDataPostAPI;
    private UploadUserFilePostAPI uploadUserFilePostAPI;
    private TextNote textNote;
    @Before
    public void init() {
        listContentsUserFolderPostAPI = APIFactory.getFilesAPI();
        createUserFolderPostAPI = APIFactory.createUserAPI();
        uploadUserFilePostAPI = APIFactory.uploadAPI();
        deleteDataPostAPI = APIFactory.deleteAPI();
    }
    @Test
    public void testListFilesFail(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        assertEquals(listContentsUserFolderPostAPI.listContentsUserFolder(newUser1.getUsername()), new ArrayList<>());
    }
    @Test
    public void testListFilesPass(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        textNote = TextNoteFactory.createTextNote("testFile1", LocalDateTime.now(),
                newUser1.getUsername(), "testData1");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        textNote = TextNoteFactory.createTextNote("testFile2", LocalDateTime.now(),
                newUser1.getUsername(), "testData2");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        textNote = TextNoteFactory.createTextNote("testFile3", LocalDateTime.now(),
                newUser1.getUsername(), "testData3");
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        ArrayList<String> fileNames = new ArrayList<>(Arrays.asList("testFile1.txt", "testFile2.txt", "testFile3.txt"));
        assertEquals(fileNames, listContentsUserFolderPostAPI.listContentsUserFolder(newUser1.getUsername()));
    }
    @After
    public void deleteTestUsers(){
        deleteDataPostAPI.deleteUser("qwerty");
    }
}
