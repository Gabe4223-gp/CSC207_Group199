package use_case.logged_in;

import java.util.ArrayList;

public interface LoggedInOutputBoundary {

    /**
     * Set the new state for the Notes view based on the arguments and switch to NoteView
     * @param username Username of the current user
     * @param currentFilename Filename of the cuurentfile to be displayed
     * @param fileTxt The text data of the current file
     * @param files All text files of the cuurent user
     */
    void prepareTextNoteView(String username, String currentFilename, String fileTxt, ArrayList<String> files);

    void prepareDrawNoteView();
}
