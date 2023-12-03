package use_case_tests;

import data_access.DBConnector;
import data_access.LoginUserDAO;
import data_access.file_read_write.FileAccessDAO;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.Assert.*;

public class LoginUseCaseTests {
    private LoginInteractor loginInteractor;
    private DBConnector dbConnector;
    private SignupViewModel signupViewModel;
    private LoginController loginController;
    private final String testUser = "123";
    private final String testPassword = "123";
    @Before
    public void loginInit(){
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        signupViewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        dbConnector = new DBConnector();
        LoginUserDAO loginUserDAO = new LoginUserDAO(dbConnector);
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loggedInViewModel, loginViewModel, signupViewModel, viewManagerModel);
        loginInteractor = new LoginInteractor(loginUserDAO, loginOutputBoundary);
        loginController = new LoginController(loginInteractor);
    }

    @Test
    public void testLoginUseCasePass() throws SQLException {
        dbConnector.dbClose();
        DBConnector dbConnector = new DBConnector();
        User user = UserFactory.createUser(testUser, testPassword);
        loginController.execute(user.getUsername(), user.getPassword());
        assertTrue(dbConnector.checkLoginCredentials(user.getUsername(), user.getPassword()));
    }
    @Test
    public void testReturnToSignupCase() {
        loginController.signupView();
        assertEquals("sign up", signupViewModel.getViewName());
    }
    @Test
    public void testLoginUseCaseUsernameFailed() throws SQLException {
        dbConnector.dbClose();
        DBConnector dbConnector = new DBConnector();
        User user = UserFactory.createUser(testUser, testPassword);
        LoginInputData loginInputData = new LoginInputData("otherUser", user.getPassword());
        loginInteractor.execute(loginInputData);
        assertFalse(dbConnector.checkLoginCredentials("otherUser", user.getPassword()));
    }
    @Test
    public void testLoginUseCasePasswordFailed() throws SQLException {
        dbConnector.dbClose();
        DBConnector dbConnector = new DBConnector();
        User user = UserFactory.createUser(testUser, testPassword);
        LoginInputData loginInputData = new LoginInputData(user.getUsername(), "otherPassword");
        loginInteractor.execute(loginInputData);
        assertFalse(dbConnector.checkLoginCredentials(user.getUsername(), "otherPassword"));
    }
    @Test
    public void testLoginUseCaseUsernameAndPasswordFailed() throws SQLException {
        dbConnector.dbClose();
        DBConnector dbConnector = new DBConnector();
        LoginInputData loginInputData = new LoginInputData("User1", "Password1");
        loginInteractor.execute(loginInputData);
        assertFalse(dbConnector.checkLoginCredentials("User1", "Password1"));
    }
    @After
    public void deleteTestFiles(){
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + testUser);
        if (f.exists()){
            try{
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
