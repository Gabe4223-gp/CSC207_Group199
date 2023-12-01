import app.LoginUseCaseFactory;
import data_access.API.DownloadUserFileGetAPI;
import data_access.API.ListContentsUserFolderPostAPI;
import data_access.API.UploadUserFilePostAPI;
import data_access.LoggedInDAO;
import data_access.SaveNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.LoggedInView;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LoggedInViewTests {
    private LoggedInView loggedInView;
    private LoggedInViewModel loggedInViewModel;
    private AllUserFilesDAO allUserFilesDAO;
    private TextNoteWriterDAO textNoteWriterDAO;
    private DownloadUserFileGetAPI downloadUserFileGetAPI;
    private ListContentsUserFolderPostAPI listContentsUserFolderPostAPI;
    private SaveNoteDAO saveNoteDAO;
    private NoteViewModel noteViewModel;

    @Before
    public void init(){
        saveNoteDAO = new SaveNoteDAO(
                new TextNoteWriterDAO(),
                allUserFilesDAO,
                new UploadUserFilePostAPI());
        TextNote testNote1 = new TextNote("TestFile1", LocalDateTime.now(), "TestUser", "TestData");
        TextNote testNote2 = new TextNote("TestFile2", LocalDateTime.now(), "TestUser", "TestData");
        TextNote testNote3 = new TextNote("TestFile3", LocalDateTime.now(), "TestUser", "TestData");
        saveNoteDAO.saveNote(testNote1);
        saveNoteDAO.saveNote(testNote2);
        saveNoteDAO.saveNote(testNote3);
        loggedInViewModel = new LoggedInViewModel();
        LoggedInState loggedInState = new LoggedInState();
        loggedInState.setUsername("TestUser");
        loggedInViewModel.setState(loggedInState);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DrawNoteViewModel drawNoteViewModel = new DrawNoteViewModel();
        allUserFilesDAO = new AllUserFilesDAO();
        textNoteWriterDAO = new TextNoteWriterDAO();
        downloadUserFileGetAPI = new DownloadUserFileGetAPI();
        listContentsUserFolderPostAPI = new ListContentsUserFolderPostAPI();
        LoggedInDAO loggedInDAO = new LoggedInDAO(allUserFilesDAO, textNoteWriterDAO, downloadUserFileGetAPI, listContentsUserFolderPostAPI);
        noteViewModel = new NoteViewModel();
        NoteState noteState = new NoteState();
        noteState.setUsername("TestUser");
        noteViewModel.setNoteState(noteState);
        loggedInView = LoginUseCaseFactory.createLoggedInView(
                loggedInViewModel,
                viewManagerModel,
                drawNoteViewModel,
                noteViewModel,
                loggedInDAO);
    }
    @Test
    public void testNoteStateOnClick(){
        loggedInView.getNoteBtn().doClick();
        assertEquals(3, noteViewModel.getNoteState().getUserFiles().size());

    }
    @Test
    public void testNoteViewChange(){
        loggedInView.getNoteBtn().doClick();
        assertEquals("notes",noteViewModel.getViewName());
    }

    @After
    public void deleteTestFiles(){
        NotesViewTests.deleteTestFilesHelper();
    }
}
