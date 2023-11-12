package app;

import data_access.DBConnector;
import data_access.LoginUserDAO;
import data_access.SignupUserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteViewModel;
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

        //Data Access objects
        LoginUserDAO loginUserDAO = new LoginUserDAO(dbConnector);

        SignupUserDAO signupUserDAO = new SignupUserDAO();

        /*
         *TODO: create data access objects
         */
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                signupViewModel,
                loginUserDAO);
        views.add(loginView, loginView.viewName);



        SignupView signupView = LoginUseCaseFactory.create(viewManagerModel,
                signupViewModel,
                loginViewModel,
                signupUserDAO);
        views.add(signupView, signupView.viewName);


        LoggedInView loggedInView = LoginUseCaseFactory.createLoggedInView(loggedInViewModel,
                viewManagerModel,
                drawNoteViewModel,
                noteViewModel);
        views.add(loggedInView, loggedInView.viewName);

        NoteView noteView = new NoteView(noteViewModel, new NoteController());
        views.add(noteView, noteView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        noDrawApp.setSize(500,500);
        noDrawApp.setMinimumSize(new Dimension(300,300));
        noDrawApp.setVisible(true);
    }
}