package interface_adapter.logged_in;
import use_case.logged_in.LoggedInInputBoundary;

public class LoggedInController {

    final LoggedInInputBoundary loggedInInputBoundary;
    public LoggedInController(LoggedInInputBoundary loggedInInputBoundary){
        this.loggedInInputBoundary = loggedInInputBoundary;
    }

    public void text_note_execute(){
        loggedInInputBoundary.text_note_execute();
    }

    public void draw_note_execute(){
        loggedInInputBoundary.draw_note_execute();
    }
}
