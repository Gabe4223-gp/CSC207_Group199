package app;

import data_access.LoginUserDAO;
import interface_adapter.ViewManagerModel;
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


        //Data Access objects
        LoginUserDAO loginUserDAO = new LoginUserDAO();
        /*
         *TODO: create data access objects
         */

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,
                loginViewModel,
                loggedInViewModel,
                loginUserDAO);
        views.add(loginView, loginView.viewName);



        SignupView signupView = new SignupView();
        views.add(signupView, signupView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, new LoggedInController());
        views.add(loggedInView, loggedInView.viewName);

        NoteView noteView = new NoteView(noteViewModel, new NoteController());
        views.add(noteView);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        noDrawApp.setSize(500,500);
        noDrawApp.setMinimumSize(new Dimension(300,300));
        noDrawApp.setVisible(true);
    }
}