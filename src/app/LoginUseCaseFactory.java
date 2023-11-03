package app;

import data_access.LoginUserDAO;
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
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoggedInView;
import view.LoginView;
import view.NoteView;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory(){}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginController loginController = createLoginUseCase(
                viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                loginUserDAO
        );
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDAO loginUserDAO
    ){
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(
                loggedInViewModel, loginViewModel,viewManagerModel
        );
        LoginInputBoundary loginInteractor = new LoginInteractor(
                loginUserDAO, loginOutputBoundary
        );
        return new LoginController(loginInteractor);
    }

    public static LoggedInView createLoggedInView(LoggedInViewModel loggedInViewModel,
                                           ViewManagerModel viewManagerModel,
                                           DrawNoteViewModel drawNoteViewModel,
                                           NoteViewModel noteViewModel){
        LoggedInController loggedInController = createLoggedInController(viewManagerModel, noteViewModel, drawNoteViewModel);
        return new LoggedInView(loggedInViewModel, loggedInController);

    }
    private static LoggedInController createLoggedInController(ViewManagerModel viewManagerModel,
                                                               NoteViewModel noteViewModel, DrawNoteViewModel drawNoteViewModel){
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(drawNoteViewModel, noteViewModel, viewManagerModel);
        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary);
        return new LoggedInController(loggedInInteractor);
    }
}
