import data_access.*;
import data_access.file_read_write.FileAccessDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import view.LoginView;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginViewTests {
    private LoginView loginView;
    private LoginUserDAO loginUserDAO;
    private SignupUserDAO signupUserDAO;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    @Before
    public void init(){
        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
    }
    @After
    public void deleteTestFiles(){
        deleteTestFilesHelper();
    }

    public static void deleteTestFilesHelper(){
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + "TestUser");
        if (f.exists()){
            try{
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
