package data_access_tests;

import data_access.API.APIFactory;
import data_access.API.UploadUserFilePostAPI;
import data_access.SaveNoteDAO;
import data_access.SelectNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import entity.TextNoteBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class SelectNoteDAOTests {
    private SaveNoteDAO saveNoteDAO;
    private AllUserFilesDAO allUserFilesDAO;
    private SelectNoteDAO selectNoteDAO;
    private final String testUser = "Test User";
    private TextNote textNote;

    @Before
    public void init(){
        allUserFilesDAO = new AllUserFilesDAO();
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        textNote = TextNoteBuilder.createTextNote("TestFile", LocalDateTime.now(),
                testUser, "TestData");
        UploadUserFilePostAPI uploadAPI = APIFactory.uploadAPI();
        saveNoteDAO = new SaveNoteDAO(textNoteWriterDAO, allUserFilesDAO, uploadAPI);
        selectNoteDAO = new SelectNoteDAO(allUserFilesDAO);
    }

    @Test
    public void testSelectReturnString(){
        saveNoteDAO.saveNote(textNote);
        assertNotNull(selectNoteDAO.getSelectedNote("TestFile", testUser));
    }

    @Test
    public void testSelectNoteSuccess(){
        TextNote textNote1 = TextNoteBuilder.createTextNote("TestFile1",
                LocalDateTime.now(),
                testUser, "TestData1");
        TextNote textNote2 = TextNoteBuilder.createTextNote("TestFile2",
                LocalDateTime.now(),
                testUser, "TestData2");
        saveNoteDAO.saveNote(textNote1);
        saveNoteDAO.saveNote(textNote2);
        String noteData1 = selectNoteDAO.getSelectedNote("TestFile1", testUser);
        String noteData2 = selectNoteDAO.getSelectedNote("TestFile2", testUser);
        assertEquals("TestData1", noteData1);
        assertEquals("TestData2", noteData2);
    }

    @After
    public void deleteTestFiles(){
        String root = FileAccessDAO.ROOT_DIR;
        File f = new File(root + testUser);
        if (f.exists()){
            try{
                FileUtils.deleteDirectory(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
