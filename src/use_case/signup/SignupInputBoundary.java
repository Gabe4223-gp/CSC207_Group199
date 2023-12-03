package use_case.signup;
/**
 *Interface used to handle with input data
 */
public interface SignupInputBoundary {
    /**
     *Method used to get private attribute username
     * @param signupInputData the input data, username, password, repeat passsword
     */
    void execute(SignupInputData signupInputData);
}
