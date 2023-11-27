package use_case.signup;

import data_access.LoginUserDAO;
import data_access.SignupUserDAO;
import entity.User;


public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final CreateUserAPIDataAccessInterface apiDataAccessInterface;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupUserDAO signupUserDAO,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupUserDAO;
        this.userPresenter = signupOutputBoundary;
        this.apiDataAccessInterface = signupUserDAO;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (this.userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }else {
            this.apiDataAccessInterface.createUserFolder(signupInputData.getUsername());
            User user = new User(signupInputData.getUsername(), signupInputData.getPassword());
            this.userDataAccessObject.save(user);
            userPresenter.prepareSuccessView("Successfully create a user");
        }
    }

    @Override
    public void back_to_login() {
        userPresenter.prepareLoginView();
    }
}
