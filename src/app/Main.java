package app;

import data_access.*;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.DeleteNoteWriterDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        JFrame noDrawApp = new JFrame("NoDraw");
        noDrawApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        noDrawApp.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout,viewManagerModel);

        //Create view models for all views
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        NoteViewModel noteViewModel = new NoteViewModel();
        DrawNoteViewModel drawNoteViewModel = new DrawNoteViewModel();


        DBConnector dbConnector = new DBConnector();
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        AllUserFilesDAO allUserFilesDAO = new AllUserFilesDAO();
        DeleteNoteWriterDAO deleteNoteWriterDAO = new DeleteNoteWriterDAO();

        //Data Access objects
        LoginUserDAO loginUserDAO = new LoginUserDAO(dbConnector);
        SignupUserDAO signupUserDAO = new SignupUserDAO(dbConnector);
        SaveNoteDAO saveNoteDAO = new SaveNoteDAO(textNoteWriterDAO, allUserFilesDAO);
        LoggedInDAO loggedInDAO = new LoggedInDAO(allUserFilesDAO);
        DeleteNoteDAO deleteNoteDAO = new DeleteNoteDAO(allUserFilesDAO, deleteNoteWriterDAO);
        SelectNoteDAO selectNoteDAO = new SelectNoteDAO(allUserFilesDAO);


        LoginView loginView = LoginUseCaseFactory.createLoginView(viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                signupViewModel,
                loginUserDAO);
        views.add(loginView, loginView.viewName);


        SignupView signupView = LoginUseCaseFactory.createSignupView(viewManagerModel,
                signupViewModel,
                loginViewModel,
                signupUserDAO);
        views.add(signupView, signupView.viewName);

        LoggedInView loggedInView = LoginUseCaseFactory.createLoggedInView(loggedInViewModel,
                viewManagerModel,
                drawNoteViewModel,
                noteViewModel,
                loggedInDAO);
        views.add(loggedInView, loggedInView.viewName);

        NoteView noteView = NotesUseCaseFactory.createNoteView(noteViewModel,viewManagerModel, saveNoteDAO, deleteNoteDAO, selectNoteDAO);
        views.add(noteView, noteView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        noDrawApp.setSize(500,500);
        noDrawApp.setMinimumSize(new Dimension(300,300));
        noDrawApp.setVisible(true);
    }
}