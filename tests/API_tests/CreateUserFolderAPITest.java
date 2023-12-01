package API_tests;

import data_access.API.CreateUserFolderPostAPI;
import org.junit.Before;

public class CreateUserFolderAPITest {
    private CreateUserFolderPostAPI createUserFolderPostAPI;
    @Before
    public void init() {
        createUserFolderPostAPI = new CreateUserFolderPostAPI();
    }
}
