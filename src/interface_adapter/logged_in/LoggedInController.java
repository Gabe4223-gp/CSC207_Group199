package interface_adapter.logged_in;
import use_case.logged_in.LoggedInInputBoundary;

public class LoggedInController {

    final LoggedInInputBoundary loggedInInteractor;
    public LoggedInController(LoggedInInputBoundary loggedInInteractor){
        this.loggedInInteractor = loggedInInteractor;
    }

    public void textNoteExecute(String username){
        loggedInInteractor.text_note_execute(username);
    }

    public void drawNoteExecute(){
        loggedInInteractor.draw_note_execute();
    }
}
