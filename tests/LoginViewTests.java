import app.LoginUseCaseFactory;
import data_access.API.CreateUserFolderPostAPI;
import data_access.DBConnector;
import data_access.LoginUserDAO;
import data_access.SignupUserDAO;
import data_access.file_read_write.FileAccessDAO;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.LoginInteractor;
import view.LoginView;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginViewTests {
    private LoginView loginView;
    private LoginUserDAO loginUserDAO;
    private SignupUserDAO signupUserDAO;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private SignupViewModel signupViewModel;

    @Before
    public void init(){
        loginViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        loginUserDAO = new LoginUserDAO(new DBConnector());
        loggedInViewModel = new LoggedInViewModel();
        signupUserDAO = new SignupUserDAO(new DBConnector(),new CreateUserFolderPostAPI());
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        User user = new User("TestUser","12345");
        loginView = LoginUseCaseFactory.createLoginView(viewManagerModel, loginViewModel,
                loggedInViewModel,signupViewModel, loginUserDAO );
        signupUserDAO.save(user);
    }

    @Test
    public void testLoginButtonWork(){
        loginView.setUsernameInput("TestUser");
        loginView.setPasswordInput("12345");
        loginView.getLoginButton().doClick();
        assertEquals("logged in", loggedInViewModel.getViewName());
    }

    @Test
    public void testSignupButtonWork(){
        loginView.getSignUpButton().doClick();
        assertEquals("sign up", signupViewModel.getViewName());
    }

    @Test
    public void testTitle(){
        assertEquals("log in", loginView.viewName);
    }

    @Test
    public void testLoginButtonExists(){
        assertNotNull(loginView.getLoginButton().getParent());
    }

    @Test
    public void testSignupButtonExists(){
        assertNotNull(loginView.getSignUpButton().getParent());
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
