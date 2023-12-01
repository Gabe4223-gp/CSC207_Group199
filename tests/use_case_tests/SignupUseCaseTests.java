package use_case_tests;

import data_access.API.CreateUserFolderPostAPI;
import data_access.DBConnector;
import data_access.LoginUserDAO;
import data_access.SaveNoteDAO;
import data_access.SignupUserDAO;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SignupUseCaseTests {

    private SignupInteractor signupInteractor;
    private DBConnector dbConnector = new DBConnector();
    private SignupUserDAO signupUserDAO = new SignupUserDAO(dbConnector, new CreateUserFolderPostAPI());
    private SignupOutputBoundary signupOutputBoundary = new SignupPresenter(new ViewManagerModel(), new SignupViewModel(), new LoginViewModel());

    @Before
    public void init() {
        signupInteractor = new SignupInteractor(signupUserDAO, signupOutputBoundary, signupUserDAO);
    }

    @Test
    public void testSignupFailExistingUsername() {
        SignupInputData signupInputData = new SignupInputData("existingUser", "password", "password");
        signupInteractor.execute(signupInputData);
        signupInteractor.execute(signupInputData);

    }

    @Test
    public void testSignupFailPasswordMismatch() {
        SignupInputData signupInputData = new SignupInputData("newUser", "password", "differentPassword");
    }

    @Test
    public void testSignupUseCasePass() throws SQLException {
        SignupInputData signupInputData = new SignupInputData("newUser",
                "password",
                "password");
        signupInteractor.execute(signupInputData);
        dbConnector.dbClose();
        dbConnector = new DBConnector();
        assertTrue(dbConnector.existsByName("newUser"));
    }

    @After
    public void deleteTestUsers() {

    }
}
