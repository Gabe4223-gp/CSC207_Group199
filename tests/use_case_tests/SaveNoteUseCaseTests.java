package use_case_tests;
import data_access.SaveNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.save_note.SaveNotePresenter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import use_case.save_note.SaveNoteInputData;
import use_case.save_note.SaveNoteInteractor;
import use_case.save_note.SaveNoteOutputBoundary;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class SaveNoteUseCaseTests {
    private SaveNoteInteractor saveNoteInteractor;
    @Before
    public void init(){
        NoteViewModel noteViewModel = new NoteViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SaveNoteDAO saveNoteDAO = new SaveNoteDAO(new TextNoteWriterDAO(), new AllUserFilesDAO());
        SaveNoteOutputBoundary saveNoteOutputBoundary = new SaveNotePresenter(noteViewModel,viewManagerModel);
        saveNoteInteractor = new SaveNoteInteractor(saveNoteOutputBoundary, saveNoteDAO);
    }
    @Test
    public void testSaveNoteUseCasePass(){
        SaveNoteInputData saveNoteInputData = new SaveNoteInputData("TestUser",
                "TestData",
                "TestFile",
                LocalDateTime.now());
        saveNoteInteractor.saveFile(saveNoteInputData);
        File f = new File(FileAccessDAO.ROOT_DIR + "TestUser" + File.separator + "TestFile" + ".txt");
        assertTrue(f.exists());
    }

    @Test
    public void testSaveNoteUseCaseFail(){
        SaveNoteInputData saveNoteInputData = new SaveNoteInputData("TestUser",
                "TestData",
                "",
                LocalDateTime.now());
        saveNoteInteractor.saveFile(saveNoteInputData);
        File f = new File(FileAccessDAO.ROOT_DIR + "TestUser" + File.separator + "TestFile" + ".txt");
        assertFalse(f.exists());
    }
    @After
    public void deleteTestFiles(){
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + "TestUser");
        if (f.exists()){
            try{
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
