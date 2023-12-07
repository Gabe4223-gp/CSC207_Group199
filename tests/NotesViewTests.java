import app.NotesUseCaseFactory;
import data_access.API.APIFactory;
import data_access.API.DeleteDataPostAPI;
import data_access.API.UploadUserFilePostAPI;
import data_access.DeleteNoteDAO;
import data_access.SaveNoteDAO;
import data_access.SelectNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.DeleteNoteWriterDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import entity.TextNoteFactory;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import view.NoteView;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class NotesViewTests {
    private NoteView noteView;
    private SaveNoteDAO saveNoteDAO;
    private DeleteNoteDAO deleteNoteDAO;
    private SelectNoteDAO selectNoteDAO;
    private ViewManagerModel viewManagerModel;
    private NoteViewModel noteViewModel;
    private AllUserFilesDAO allUserFilesDAO;

    @Before
    public void init(){
        noteViewModel = new NoteViewModel();
        viewManagerModel = new ViewManagerModel();
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        allUserFilesDAO = new AllUserFilesDAO();
        DeleteNoteWriterDAO deleteNoteWriterDAO = new DeleteNoteWriterDAO();
        UploadUserFilePostAPI uploadAPI = APIFactory.uploadAPI();
        DeleteDataPostAPI deleteAPI = APIFactory.deleteAPI();
        saveNoteDAO = new SaveNoteDAO(textNoteWriterDAO,allUserFilesDAO, uploadAPI);
        TextNote textNote = TextNoteFactory.createTextNote("TestFile",
                LocalDateTime.now(),
                "TestUser", "Test Data");
        TextNote textNote1 = TextNoteFactory.createTextNote("TestFile1",
                LocalDateTime.now(),
                "TestUser", "Test Data1");
        TextNote textNote2 = TextNoteFactory.createTextNote("TestFile2",
                LocalDateTime.now(),
                "TestUser", "Test Data2");
        saveNoteDAO.saveNote(textNote1);
        saveNoteDAO.saveNote(textNote2);
        saveNoteDAO.saveNote(textNote);
        deleteNoteDAO = new DeleteNoteDAO(allUserFilesDAO,deleteNoteWriterDAO, deleteAPI);
        selectNoteDAO = new SelectNoteDAO(allUserFilesDAO);
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("TestFile");
        fileList.add("TestFile1");
        fileList.add("TestFile2");
        NoteState noteState = new NoteState();
        noteState.setUsername("TestUser");
        noteState.setFilename("TestFile");
        noteState.setFileTxt("TestData");
        noteState.setUserFiles(fileList);
        noteViewModel.setNoteState(noteState);
        noteView = NotesUseCaseFactory.createNoteView(noteViewModel,
                viewManagerModel,
                saveNoteDAO,
                deleteNoteDAO,
                selectNoteDAO);
    }

    @Test
    public void testSaveBtnClickFileNotExists(){
        noteView.getSave().doClick();
        File f = new File(FileAccessDAO.ROOT_DIR + "TestUser" + File.separator + "TestFile3" + ".txt");
        assertFalse(f.exists());
    }
    @Test
    public void testSaveBtnClickFileExists(){
        noteView.getSave().doClick();
        File f = new File(FileAccessDAO.ROOT_DIR + "TestUser" + File.separator + "TestFile2" + ".txt");
        assertTrue(f.exists());
    }

    @Test
    public void testDeleteBtnDeletes(){
        noteView.getDeleteBtn().doClick();
        assertFalse(noteViewModel.getNoteState().getUserFiles().contains("TestFile"));
    }

    @After
    public void deleteTestFiles(){
        deleteTestFilesHelper();
    }

    public static void deleteTestFilesHelper(){
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
