package use_case.logged_in;

import java.io.IOException;

public interface LoggedInInputBoundary {

    void text_note_execute(String username) throws IOException;

    void draw_note_execute();
}
