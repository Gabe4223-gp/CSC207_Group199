package data_access_tests;
import data_access.API.UploadUserFilePostAPI;
import data_access.SaveNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import entity.TextNoteBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SaveNoteDAOTests {
    private SaveNoteDAO saveNoteDAO;
    private AllUserFilesDAO allUserFilesDAO;
    private final String testUser = "Test User";
    private TextNote textNote;
    @Before
    public void init() {
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        allUserFilesDAO = new AllUserFilesDAO();
        textNote = TextNoteBuilder.createTextNote("TestFile",
                LocalDateTime.now(),
                testUser, "Test Data");
        saveNoteDAO = new SaveNoteDAO(textNoteWriterDAO, allUserFilesDAO, new UploadUserFilePostAPI());
    }
    @Test
    public void testSaveNoteReturnsTrue(){
        assertTrue(saveNoteDAO.saveNote(textNote));
    }
    @Test
    public void testFileExistsAfterSave(){
        saveNoteDAO.saveNote(textNote);
        File f = new File(FileAccessDAO.ROOT_DIR+testUser+ File.separator + textNote.getFileName() + ".txt");
        assertTrue(f.exists());
    }
    @Test
    public void testFileDataSaves(){
        saveNoteDAO.saveNote(textNote);
        String fileTxt = allUserFilesDAO.getFileData(testUser, textNote.getFileName());
        assertEquals(textNote.getFileTxt(), fileTxt);
    }

    @Test
    public void testMultipleFilesSave(){
        TextNote textNote1 = TextNoteBuilder.createTextNote("TestFile1",
                LocalDateTime.now(),
                testUser, "Test Data1");
        TextNote textNote2 = TextNoteBuilder.createTextNote("TestFile2",
                LocalDateTime.now(),
                testUser, "Test Data2");
        saveNoteDAO.saveNote(textNote);
        saveNoteDAO.saveNote(textNote1);
        saveNoteDAO.saveNote(textNote2);
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("TestFile");
        fileList.add("TestFile1");
        fileList.add("TestFile2");
        ArrayList<String> userFiles = saveNoteDAO.getAllUserFiles(testUser);
        assertEquals(userFiles.size(), fileList.size());
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
