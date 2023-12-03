package use_case.signup;
/**
 *Interface used to handle with output data
 */
public interface SignupOutputBoundary {

    /**
     *Method prepares successful view when all conditions are met and pass
     * signup output data to display the view
     * @param signupOutputData output message
     */
    void prepareSuccessView(SignupOutputData signupOutputData);

    /**
     *Method prepares failed view when one of conditions is not met and pass
     * error to display the message
     * @param error message shows which part is wrong
     */
    void prepareFailView(String error);

}
