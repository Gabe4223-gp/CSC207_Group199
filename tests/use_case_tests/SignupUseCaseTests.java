package use_case_tests;

import data_access.API.APIFactory;
import data_access.API.CreateUserFolderPostAPI;
import data_access.API.DeleteDataPostAPI;
import data_access.DBConnector;

import data_access.SignupUserDAO;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupState;
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

    private SignupState signupState = new SignupState();

    private SignupViewModel signupViewModel = new SignupViewModel();

    private SignupOutputBoundary signupOutputBoundary = new SignupPresenter(new ViewManagerModel(), signupViewModel, new LoginViewModel());
    private DeleteDataPostAPI deleteDataPostAPI;
    @Before
    public void init() {
        CreateUserFolderPostAPI createAPI = APIFactory.createUserAPI();
        SignupUserDAO signupUserDAO = new SignupUserDAO(dbConnector, createAPI);
        signupInteractor = new SignupInteractor(signupUserDAO, signupOutputBoundary);
        deleteDataPostAPI = APIFactory.deleteAPI();
    }

    @Test
    public void testSignupFailExistingUsername() {
        SignupInputData signupInputData = new SignupInputData("abc", "abc", "abc");
        signupInteractor.execute(signupInputData);
        assertEquals("User already exists.", signupViewModel.getSignupState().getError());
    }

    @Test
    public void testSignupFailPasswordMismatch() {
        SignupInputData signupInputData = new SignupInputData("newUser", "password", "differentPassword");
        signupInteractor.execute(signupInputData);
        assertEquals("Passwords don't match.", signupViewModel.getSignupState().getError());
    }

    @Test
    public void testSignupUseCasePass() throws SQLException {
        SignupInputData signupInputData = new SignupInputData("newUser2",
                "password",
                "password");
        signupInteractor.execute(signupInputData);
        DBConnector dbConnector1 = new DBConnector();
        assertTrue(dbConnector1.existsByName("newUser2"));
        dbConnector1.dbClose();
    }
    @After
    public void clearDatabase(){
        dbConnector.deleteUser("newUser");
        dbConnector.deleteUser("newUser2");
        deleteDataPostAPI.deleteUser("newUser");
        deleteDataPostAPI.deleteUser("newUser2");
    }
}
