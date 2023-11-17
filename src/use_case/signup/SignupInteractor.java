package use_case.signup;

import entity.User;


public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }else {

            User user = new User();
            user.setUsername(signupInputData.getUsername());
            user.setPassword(signupInputData.getPassword());
            userDataAccessObject.save(user);
            userPresenter.prepareSuccessView("Successfully create a user");
        }
    }

    @Override
    public void back_to_login() {
        userPresenter.prepareLoginView();
    }
}
