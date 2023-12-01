import app.NotesUseCaseFactory;
import data_access.*;
import data_access.API.DeleteDataPostAPI;
import data_access.API.UploadUserFilePostAPI;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.DeleteNoteWriterDAO;
import data_access.file_read_write.FileAccessDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import entity.TextNote;
import entity.TextNoteFactory;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.LoginView;
import view.NoteView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginViewTests {
    private LoginView loginView;
    private LoginUserDAO loginUserDAO;
    private SignupUserDAO signupUserDAO;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    @Before
    public void init(){
        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
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
