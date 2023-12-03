package API_tests;

import data_access.API.APIFactory;
import data_access.API.CreateUserFolderPostAPI;
import data_access.API.DeleteDataPostAPI;
import entity.User;
import entity.UserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateUserFolderAPITest {
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    private DeleteDataPostAPI deleteDataPostAPI;
    String user = "userTest1";

    @Before
    public void init() {
        createUserFolderPostAPI = APIFactory.createUserAPI();
        deleteDataPostAPI = APIFactory.deleteAPI();
    }
    @Test
    public void testCreateUserFolderPass() {
        User newUser1 = UserFactory.createUser("qwerty", "uiop");
        User newUser2 = UserFactory.createUser("asdfg", "hjkl");
        User newUser3 = UserFactory.createUser("zxcv", "bnm");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(newUser1, newUser2, newUser3));
        for (User thisUser : users) {
            assertTrue(createUserFolderPostAPI.createUserFolder(thisUser.getUsername()));
        }
    }
    @Test
    public void testCreateUserFolderFail() {
        createUserFolderPostAPI.createUserFolder(user);
        assertFalse(createUserFolderPostAPI.createUserFolder(user));
    }
    @After
    public void deleteTestUsers(){
        deleteDataPostAPI.deleteUser(user);
        deleteDataPostAPI.deleteUser("qwerty");
        deleteDataPostAPI.deleteUser("asdfg");
        deleteDataPostAPI.deleteUser("zxcv");
    }
}
