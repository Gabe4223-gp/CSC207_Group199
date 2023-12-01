package factory_tests;

import app.LoginUseCaseFactory;
import data_access.*;
import data_access.API.*;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;

import static org.junit.Assert.assertNotSame;

public class LoginUseCaseFactoryTests {
    @Test
    public void testCreateNoteViewUseCase(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        NoteViewModel noteViewModel = new NoteViewModel();
        DrawNoteViewModel drawNoteViewModel = new DrawNoteViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        AllUserFilesDAO allUserFilesDAO = new AllUserFilesDAO();
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        LoggedInDAO loggedInDAO = new LoggedInDAO(allUserFilesDAO, textNoteWriterDAO, new DownloadUserFileGetAPI(), new ListContentsUserFolderPostAPI());
        DBConnector dbConnector = new DBConnector();
        LoginUserDAO loginUserDAO = new LoginUserDAO(dbConnector);
        SignupUserDAO signupUserDAO = new SignupUserDAO(dbConnector, new CreateUserFolderPostAPI());
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(drawNoteViewModel, noteViewModel, viewManagerModel);
        LoggedInInputBoundary loggedInInputBoundary = new LoggedInInteractor(loggedInOutputBoundary, loggedInDAO);
        LoggedInController loggedInController = new LoggedInController(loggedInInputBoundary);
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loggedInViewModel, loginViewModel, signupViewModel, viewManagerModel);
        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginUserDAO, loginOutputBoundary);
        LoginController loginController = new LoginController(loginInputBoundary);
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary signupInputBoundary = new SignupInteractor(signupUserDAO, signupOutputBoundary);
        SignupController signupController = new SignupController(signupInputBoundary);

        SignupView signupView1 = new SignupView(signupController, signupViewModel);
        SignupView signupView2 = LoginUseCaseFactory.createSignupView(viewManagerModel,
                signupViewModel, loginViewModel, signupUserDAO);
        assertNotSame(signupView2, signupView1);

        LoginView loginView1 = new LoginView(loginViewModel, loginController);
        LoginView loginView2 = LoginUseCaseFactory.createLoginView(viewManagerModel,
                loginViewModel, loggedInViewModel, signupViewModel, loginUserDAO);
        assertNotSame(loginView2, loginView1);

        LoggedInView loggedInView1 = new LoggedInView(loggedInViewModel, loggedInController);
        LoggedInView loggedInView2 = LoginUseCaseFactory.createLoggedInView(loggedInViewModel,
                viewManagerModel, drawNoteViewModel, noteViewModel, loggedInDAO);
        assertNotSame(loggedInView2, loggedInView1);
    }
}
