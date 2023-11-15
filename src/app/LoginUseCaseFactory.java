package app;

import data_access.LoggedInDAO;
import data_access.LoginUserDAO;
import data_access.SignupUserDAO;
import entity.Note;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LoggedInView;
import view.LoginView;
import view.NoteView;
import view.SignupView;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory(){}

    public static SignupView createSignupView(ViewManagerModel viewManagerModel,
                                    SignupViewModel signupViewModel,
                                    LoginViewModel loginViewModel,
                                    SignupUserDAO signupDataAccessInterface){
        SignupController signupController = createSignupUseCase(
                viewManagerModel,
                signupViewModel,
                loginViewModel,
                signupDataAccessInterface
        );
        return new SignupView(signupController,signupViewModel);
    }

    private static SignupController createSignupUseCase(ViewManagerModel viewManagerModel,
                                                        SignupViewModel signupViewModel,
                                                        LoginViewModel loginViewModel,
                                                        SignupUserDAO signupDataAccessInterface){
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
                viewManagerModel,signupViewModel,loginViewModel
        );
        SignupInputBoundary signupInterator = new SignupInteractor(
                signupDataAccessInterface,signupOutputBoundary
        );
        return new SignupController(signupInterator);
    }

    public static LoginView createLoginView(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            SignupViewModel signupViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginController loginController = createLoginUseCase(
                viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                signupViewModel,
                loginUserDAO
        );
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            SignupViewModel signupViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(
                loggedInViewModel, loginViewModel,signupViewModel,viewManagerModel
        );
        LoginInputBoundary loginInteractor = new LoginInteractor(
                loginUserDAO, loginOutputBoundary
        );
        return new LoginController(loginInteractor);
    }

    public static LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel,
                                                  ViewManagerModel viewManagerModel,
                                                  DrawNoteViewModel drawNoteViewModel,
                                                  NoteViewModel noteViewModel,
                                                  LoggedInDAO loggedInDAO){
        LoggedInController loggedInController =
                createLoggedInController(viewManagerModel, noteViewModel, drawNoteViewModel, loggedInDAO);
        return new LoggedInView(loggedInViewModel, loggedInController);

    }
    private static LoggedInController createLoggedInController(ViewManagerModel viewManagerModel,
                                                               NoteViewModel noteViewModel,
                                                               DrawNoteViewModel drawNoteViewModel,
                                                               LoggedInDAO loggedInDAO){
        LoggedInOutputBoundary loggedInOutputBoundary =
                new LoggedInPresenter(drawNoteViewModel, noteViewModel, viewManagerModel);
        LoggedInInputBoundary loggedInInteractor =
                new LoggedInInteractor(loggedInOutputBoundary, loggedInDAO);
        return new LoggedInController(loggedInInteractor);
    }
}
