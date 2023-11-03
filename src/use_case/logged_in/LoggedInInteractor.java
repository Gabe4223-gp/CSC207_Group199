package use_case.logged_in;

public class LoggedInInteractor implements LoggedInInputBoundary{

    private final LoggedInOutputBoundary loggedInOutputBoundary;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.loggedInOutputBoundary = loggedInOutputBoundary;
    }

    @Override
    public void text_note_execute() {
        loggedInOutputBoundary.prepareTextNoteView();
    }

    @Override
    public void draw_note_execute() {
        loggedInOutputBoundary.prepareDrawNoteView();
    }

}
