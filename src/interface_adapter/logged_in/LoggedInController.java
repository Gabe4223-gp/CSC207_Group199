package interface_adapter.logged_in;
import use_case.logged_in.LoggedInInputBoundary;

import java.io.IOException;

public class LoggedInController {

    final LoggedInInputBoundary loggedInInteractor;
    public LoggedInController(LoggedInInputBoundary loggedInInteractor){
        this.loggedInInteractor = loggedInInteractor;
    }

    public void textNoteExecute(String username) throws IOException {
        loggedInInteractor.text_note_execute(username);
    }

    public void drawNoteExecute(){
        loggedInInteractor.draw_note_execute();
    }
}
