package factory_tests;

import app.NotesUseCaseFactory;
import data_access.API.DeleteDataPostAPI;
import data_access.API.UploadUserFilePostAPI;
import data_access.DeleteNoteDAO;
import data_access.SaveNoteDAO;
import data_access.SelectNoteDAO;
import data_access.file_read_write.AllUserFilesDAO;
import data_access.file_read_write.DeleteNoteWriterDAO;
import data_access.file_read_write.TextNoteWriterDAO;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_note.DeleteNoteController;
import interface_adapter.delete_note.DeleteNotePresenter;
import interface_adapter.save_note.SaveNoteController;
import interface_adapter.save_note.SaveNotePresenter;
import interface_adapter.select_note.SelectNoteController;
import interface_adapter.select_note.SelectNotePresenter;
import org.junit.Test;
import use_case.delete_note.DeleteNoteInputBoundary;
import use_case.delete_note.DeleteNoteInteractor;
import use_case.delete_note.DeleteNoteOutputBoundary;
import use_case.save_note.SaveNoteInputBoundary;
import use_case.save_note.SaveNoteInteractor;
import use_case.save_note.SaveNoteOutputBoundary;
import use_case.select_note.SelectNoteInputBoundary;
import use_case.select_note.SelectNoteInteractor;
import use_case.select_note.SelectNoteOutputBoundary;
import view.NoteView;

import static org.junit.Assert.*;

public class NotesUseCaseFactoryTests {
    @Test
    public void testCreateNoteViewUseCase(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        NoteViewModel noteViewModel = new NoteViewModel();
        TextNoteWriterDAO textNoteWriterDAO = new TextNoteWriterDAO();
        AllUserFilesDAO allUserFilesDAO = new AllUserFilesDAO();
        DeleteNoteWriterDAO deleteNoteWriterDAO = new DeleteNoteWriterDAO();
        SaveNoteDAO saveNoteDAO = new SaveNoteDAO(textNoteWriterDAO,allUserFilesDAO, new UploadUserFilePostAPI());
        DeleteNoteDAO deleteNoteDAO = new DeleteNoteDAO(allUserFilesDAO,deleteNoteWriterDAO, new DeleteDataPostAPI());
        SaveNoteOutputBoundary saveNoteOutputBoundary = new SaveNotePresenter(noteViewModel,viewManagerModel);
        SaveNoteInputBoundary saveNoteInputBoundary = new SaveNoteInteractor(saveNoteOutputBoundary, saveNoteDAO, saveNoteDAO);
        SaveNoteController saveNoteController = new SaveNoteController(saveNoteInputBoundary);
        DeleteNoteOutputBoundary deleteNoteOutputBoundary = new DeleteNotePresenter(noteViewModel, viewManagerModel);
        DeleteNoteInputBoundary deleteNoteInputBoundary = new DeleteNoteInteractor(deleteNoteOutputBoundary,deleteNoteDAO,
                deleteNoteDAO);
        DeleteNoteController deleteNoteController = new DeleteNoteController(deleteNoteInputBoundary);
        SelectNoteDAO selectNoteDAO = new SelectNoteDAO(allUserFilesDAO);
        SelectNoteOutputBoundary selectNoteOutputBoundary = new SelectNotePresenter(noteViewModel,viewManagerModel);
        SelectNoteInputBoundary selectNoteInputBoundary = new SelectNoteInteractor(selectNoteDAO,selectNoteOutputBoundary);
        SelectNoteController selectNoteController = new SelectNoteController(selectNoteInputBoundary);
        NoteView noteView1 = new NoteView(
                noteViewModel,
                saveNoteController,
                deleteNoteController,
                selectNoteController
        );
        NoteView noteView2 = NotesUseCaseFactory.createNoteView(noteViewModel,
                viewManagerModel,
                saveNoteDAO,
                deleteNoteDAO,
                selectNoteDAO);
        assertNotSame(noteView2, noteView1);
    }
}
