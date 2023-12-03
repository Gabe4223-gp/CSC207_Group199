package API_tests;

import data_access.API.APIFactory;
import data_access.API.CreateUserFolderPostAPI;
import data_access.API.DeleteDataPostAPI;
import data_access.API.UploadUserFilePostAPI;
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

public class DeleteDataAPITest {
    private DeleteDataPostAPI deleteDataPostAPI;
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    private UploadUserFilePostAPI uploadUserFilePostAPI;
    private TextNote textNote;
    String user = "userTest2";
    String filename = "userFileTest";
    String fileTxt = "test data";

    @Before
    public void init() {
        createUserFolderPostAPI = APIFactory.createUserAPI();
        deleteDataPostAPI = APIFactory.deleteAPI();
        uploadUserFilePostAPI = APIFactory.uploadAPI();
    }
    @Test
    public void testDeleteUserFileFail() {
        assertFalse(deleteDataPostAPI.deleteUserFile(user, filename));
    }
    @Test
    public void testDeleteUserFail() {
        assertFalse(deleteDataPostAPI.deleteUser(user));
    }
    @Test
    public void testDeleteUserPass() {
        User newUser = UserFactory.createUser("asdfg", "hjkl");
        createUserFolderPostAPI.createUserFolder(newUser.getUsername());
        assertTrue(deleteDataPostAPI.deleteUser(newUser.getUsername()));
    }
    @Test
    public void testDeleteUserFilePass() {
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        createUserFolderPostAPI.createUserFolder(newUser1.getUsername());
        textNote = TextNoteFactory.createTextNote(filename, LocalDateTime.now(),
                newUser1.getUsername(), fileTxt);
        uploadUserFilePostAPI.uploadUserFile(newUser1.getUsername(), textNote);
        assertTrue(deleteDataPostAPI.deleteUserFile(newUser1.getUsername(), filename));
    }
    @After
    public void deleteTestUsers(){
        deleteDataPostAPI.deleteUser("qwerty");
    }
}
