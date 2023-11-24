package use_case.signup;

import entity.User;

import java.time.LocalDateTime;

/**
 * Sign Up Use Case interactor to use the signup data access interface and tell the presenter to prepare success or
 * fail views.
 */
public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    /**
     * Transfer the username, password, repeated password to database using the data access object, check whether
     * the username is overlapped or passwords don't match
     * prepare successful view or failed view according to above results
     * @param signupInputData The input data from the controller to be used to save the user
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (this.userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }else {
            User user = new User(signupInputData.getUsername(), signupInputData.getPassword());
            this.userDataAccessObject.save(user);

            LocalDateTime now = LocalDateTime.now();
            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

}