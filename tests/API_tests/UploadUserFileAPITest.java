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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UploadUserFileAPITest {
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    private UploadUserFilePostAPI uploadUserFilePostAPI;
    private DeleteDataPostAPI deleteDataPostAPI;
    private TextNote textNote;
    @Before
    public void init() {
        createUserFolderPostAPI = APIFactory.createUserAPI();
        uploadUserFilePostAPI = APIFactory.uploadAPI();
        deleteDataPostAPI = APIFactory.deleteAPI();
    }
    @Test
    public void testUploadFilePass(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        textNote = TextNoteFactory.createTextNote("testFile1", LocalDateTime.now(),
                newUser1.getUsername(), "testData1");
        assertTrue(uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote));
    }
    @Test
    public void testUploadFileFail(){
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        textNote = TextNoteFactory.createTextNote("testFile1", LocalDateTime.now(),
                newUser1.getUsername(), "testData1");
        assertFalse(uploadUserFilePostAPI.uploadUserFile("userTest1", textNote));
    }
    @After
    public void deleteTestUsers(){
        deleteDataPostAPI.deleteUser("qwerty");
    }
}
