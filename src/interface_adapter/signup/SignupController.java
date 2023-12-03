package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Class used by user to make decisions based on provided choices
 */
public class SignupController {

    final SignupInputBoundary signupInteractor;

    /**
     *Initialize the signup controller with signup input boundary
     */
    public SignupController(SignupInputBoundary signupInteractor){
        this.signupInteractor = signupInteractor;
    }

    /**
     *Method used to pass the command and information based on user's choice
     * @param username username of the user
     * @param password password of the user
     * @param repeatPassword repeat password of the user
     */
    public void execute(String username, String password, String repeatPassword) {
        SignupInputData signupInputData = new SignupInputData(username,password,repeatPassword);
        signupInteractor.execute(signupInputData);
    }

}
