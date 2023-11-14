package use_case.logged_in;

public interface LoggedInOutputBoundary {

    void prepareTextNoteView(String username, String currentFilename, String fileTxt);

    void prepareDrawNoteView();
}
