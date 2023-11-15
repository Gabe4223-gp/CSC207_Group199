package use_case.logged_in;

import entity.TextNote;
import entity.User;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Use case interactor for the currently logged-in User.
 * Executes for text note or draw note based on the user selection
 */
public class LoggedInInteractor implements LoggedInInputBoundary{

    private final LoggedInOutputBoundary loggedInPresenter;
    private final LoggedInDataAccessInterface loggedInDAO;
    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary, LoggedInDataAccessInterface loggedInDAO) {
        this.loggedInPresenter = loggedInOutputBoundary;
        this.loggedInDAO = loggedInDAO;
    }


    @Override
    public void text_note_execute(String username) {
        //Test Data
        ArrayList<String> files = loggedInDAO.getUserFiles(username);
        if(!files.isEmpty()){
            TextNote textNote = loggedInDAO.getTextNote(files.get(0), username);
            loggedInPresenter.prepareTextNoteView(textNote.getCreatedUser(),
                    textNote.getFileName(),
                    textNote.getFileTxt(),
                    files);
        }else{
            loggedInPresenter.prepareTextNoteView(username,
                    "",
                    "",
                    new ArrayList<>());
        }
    }

    @Override
    public void draw_note_execute() {
        loggedInPresenter.prepareDrawNoteView();
    }

}
