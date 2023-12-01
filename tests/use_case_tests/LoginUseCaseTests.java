package use_case_tests;

import data_access.DBConnector;
import data_access.LoginUserDAO;
import data_access.file_read_write.FileAccessDAO;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.*;
import use_case.signup.SignupInteractor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginUseCaseTests {
    private LoginInteractor loginInteractor;
    private SignupInteractor signupInteractor;
    private DBConnector dbConnector;
    @Before
    public void loginInit(){
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        dbConnector = new DBConnector();
        LoginUserDAO loginUserDAO = new LoginUserDAO(dbConnector);
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loggedInViewModel, loginViewModel, signupViewModel, viewManagerModel);
        loginInteractor = new LoginInteractor(loginUserDAO, loginOutputBoundary);
        //signupInteractor = new SignupInteractor()
    }

    @Test
    public void testLoginUseCasePass() throws SQLException {
        User user = new User("user", "password");
        dbConnector.dbClose();
        DBConnector dbConnector = new DBConnector();
        LoginInputData loginInputData = new LoginInputData(user.getUsername(), user.getPassword());
        loginInteractor.execute(loginInputData);
        assertTrue(dbConnector.checkLoginCredentials(user.getUsername(), user.getPassword()));
    }
    @Test
    public void testLoginUseCaseUsernameFailed(){
        LoginInputData loginInputData = new LoginInputData("MockUser", "MockPassword");
        LoginUserDAO loginUserDAO = new LoginUserDAO(new DBConnector());
        loginInteractor.execute(loginInputData);
    }
    @Test
    public void testLoginUseCasePasswordFailed(){
        LoginInputData loginInputData = new LoginInputData("MockUser", "MockPassword");
        LoginUserDAO loginUserDAO = new LoginUserDAO(new DBConnector());
        loginInteractor.execute(loginInputData);
    }
    @Test
    public void testLoginUseCaseUsernameAndPasswordFailed(){
        LoginInputData loginInputData = new LoginInputData("MockUser", "MockPassword");
        LoginUserDAO loginUserDAO = new LoginUserDAO(new DBConnector());
        loginInteractor.execute(loginInputData);
    }
    @After
    public void deleteTestFiles(){
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + "MockUser");
        if (f.exists()){
            try{
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
