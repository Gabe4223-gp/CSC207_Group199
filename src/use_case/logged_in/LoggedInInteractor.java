package use_case.logged_in;

import java.util.ArrayList;

/**
 * Use case interactor for the currently logged-in User.
 * Executes for text note or draw note based on the user selection
 */
public class LoggedInInteractor implements LoggedInInputBoundary{

    private final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.loggedInPresenter = loggedInOutputBoundary;
    }


    @Override
    public void text_note_execute(String username) {
        //Test Data
        String filename = "TestFilename";
        String fileTxt = "asdlikjyfutgdsaliuyjfgt";
        ArrayList<String> files = new ArrayList<>();
        files.add("sdhgrf");
        files.add("sdkjyftg");
        //TODO: Get filename file txt and list to give to the presenter
        loggedInPresenter.prepareTextNoteView(username,filename,fileTxt, files);
    }

    @Override
    public void draw_note_execute() {
        loggedInPresenter.prepareDrawNoteView();
    }

}
