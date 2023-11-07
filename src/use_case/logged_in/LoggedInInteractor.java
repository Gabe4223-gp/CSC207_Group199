package use_case.logged_in;

public class LoggedInInteractor implements LoggedInInputBoundary{

    private final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.loggedInPresenter = loggedInOutputBoundary;
    }

    @Override
    public void text_note_execute(String username) {
        loggedInPresenter.prepareTextNoteView();
    }

    @Override
    public void draw_note_execute() {
        loggedInPresenter.prepareDrawNoteView();
    }

}
