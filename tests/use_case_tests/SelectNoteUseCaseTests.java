package use_case_tests;

import data_access.API.UploadUserFilePostAPI;
import data_access.SaveNoteDAO;
import data_access.SelectNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.save_note.SaveNotePresenter;
import interface_adapter.select_note.SelectNoteController;
import interface_adapter.select_note.SelectNotePresenter;
import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.save_note.SaveNoteInputData;
import use_case.save_note.SaveNoteInteractor;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.select_note.SelectNoteInputData;
import use_case.select_note.SelectNoteInteractor;
import use_case.select_note.SelectNoteOutputBoundary;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class SelectNoteUseCaseTests {

    private SelectNoteInteractor selectNoteInteractor;

    private SaveNoteInteractor saveNoteInteractor;

    private SelectNoteDAO selectNoteDAO;

    private NoteViewModel noteViewModel;

    private SelectNoteController selectNoteController;

    @Before
    public void init(){
        noteViewModel = new NoteViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        selectNoteDAO = new SelectNoteDAO(new AllUserFilesDAO());
        SelectNoteOutputBoundary selectNoteOutputBoundary = new SelectNotePresenter(noteViewModel,viewManagerModel);
        selectNoteInteractor= new SelectNoteInteractor(selectNoteDAO, selectNoteOutputBoundary);
        selectNoteController = new SelectNoteController(selectNoteInteractor);
        SaveNoteDAO saveNoteDAO = new SaveNoteDAO(new TextNoteWriterDAO(), new AllUserFilesDAO(), new UploadUserFilePostAPI());
        SaveNoteOutputBoundary saveNoteOutputBoundary = new SaveNotePresenter(noteViewModel,viewManagerModel);
        saveNoteInteractor = new SaveNoteInteractor(saveNoteOutputBoundary, saveNoteDAO, saveNoteDAO);

    }

    @Test
    public void  testSelectNoteUseCasePass(){
        SaveNoteInputData saveNoteInputData1 = new SaveNoteInputData("TestUser",
                "TestData1",
                "TestFile1",
                LocalDateTime.now());
        SaveNoteInputData saveNoteInputData2 = new SaveNoteInputData("TestUser",
                "TestData2",
                "TestFile2",
                LocalDateTime.now());
        saveNoteInteractor.saveFile(saveNoteInputData1);
        saveNoteInteractor.saveFile(saveNoteInputData2);
        SelectNoteInputData selectNoteInputData1 = new SelectNoteInputData("TestFile1", 0, "TestUser");
        String selectedNoteData1 = selectNoteDAO.getSelectedNote(selectNoteInputData1.getFilename(), selectNoteInputData1.getUsername());
        assertEquals(selectedNoteData1, saveNoteInputData1.getNoteData());
        SelectNoteInputData selectNoteInputData2 = new SelectNoteInputData("TestFile2", 1, "TestUser");
        String selectedNoteData2 = selectNoteDAO.getSelectedNote(selectNoteInputData2.getFilename(), selectNoteInputData2.getUsername());
        assertEquals(selectedNoteData2, saveNoteInputData2.getNoteData());
        NoteState noteState = noteViewModel.getNoteState();
        selectNoteController.selectNote("TestFile1", 0, "TestUser");
        assertEquals("TestData1", noteState.getFileTxt());
        assertEquals("TestFile1", noteState.getFilename());
        assertEquals("TestUser", noteState.getUsername());
        selectNoteController.selectNote("TestFile2", 1, "TestUser");
        assertEquals("TestData2", noteState.getFileTxt());
        assertEquals("TestFile2", noteState.getFilename());
        assertEquals("TestUser", noteState.getUsername());


    }
    @Test
    public void testSelectNoteUseCaseFail() {
        SaveNoteInputData saveNoteInputData1 = new SaveNoteInputData("TestUser",
                "TestData1",
                "TestFile1",
                LocalDateTime.now());
        SaveNoteInputData saveNoteInputData2 = new SaveNoteInputData("TestUser",
                "TestData2",
                "TestFile2",
                LocalDateTime.now());

        saveNoteInteractor.saveFile(saveNoteInputData1);
        saveNoteInteractor.saveFile(saveNoteInputData2);
        SelectNoteInputData selectNoteInputData1 = new SelectNoteInputData("TestFile1", -1, "TestUser");
        selectNoteInteractor.selectNote(selectNoteInputData1);
        NoteState noteState = noteViewModel.getNoteState();
        assertEquals("Please select a note", noteState.getError());
    }

    @After
    public void deleteTestFiles() {
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + "TestUser");
        if (f.exists()) {
            try {
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

