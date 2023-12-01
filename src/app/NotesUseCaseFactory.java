package app;

import data_access.DeleteNoteDAO;
import data_access.SaveNoteDAO;
import data_access.SelectNoteDAO;
import interface_adapter.NoteViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_note.DeleteNoteController;
import interface_adapter.delete_note.DeleteNotePresenter;
import interface_adapter.save_note.SaveNoteController;
import interface_adapter.save_note.SaveNotePresenter;
import interface_adapter.select_note.SelectNoteController;
import interface_adapter.select_note.SelectNotePresenter;
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

public class NotesUseCaseFactory {
    private NotesUseCaseFactory(){}

    public static NoteView createNoteView(NoteViewModel noteViewModel,
                                          ViewManagerModel viewManagerModel,
                                          SaveNoteDAO saveNoteDAO,
                                          DeleteNoteDAO deleteNoteDAO, SelectNoteDAO selectNoteDAO){
        return new NoteView(noteViewModel,
                createSaveNoteUseCase(viewManagerModel, noteViewModel,saveNoteDAO),
                createDeleteNoteUseCase(viewManagerModel,noteViewModel, deleteNoteDAO),
                createSelectNoteUseCase(viewManagerModel,noteViewModel,selectNoteDAO));
    }

    public static SaveNoteController createSaveNoteUseCase(
            ViewManagerModel viewManagerModel,
            NoteViewModel noteViewModel,
            SaveNoteDAO saveNoteDAO
    ){
        SaveNoteOutputBoundary saveNoteOutputBoundary = new SaveNotePresenter(noteViewModel,viewManagerModel);
        SaveNoteInputBoundary saveNoteInteractor = new SaveNoteInteractor(saveNoteOutputBoundary, saveNoteDAO);
        return new SaveNoteController(saveNoteInteractor);
    }

    public static DeleteNoteController createDeleteNoteUseCase(
            ViewManagerModel viewManagerModel,
            NoteViewModel noteViewModel,
            DeleteNoteDAO deleteNoteDAO
    ){
        DeleteNoteOutputBoundary deleteNoteOutputBoundary = new DeleteNotePresenter(noteViewModel, viewManagerModel);
        DeleteNoteInputBoundary deleteNoteInteractor = new DeleteNoteInteractor(deleteNoteOutputBoundary,deleteNoteDAO);
        return new DeleteNoteController(deleteNoteInteractor);
    }

    public static SelectNoteController createSelectNoteUseCase(
            ViewManagerModel viewManagerModel,
            NoteViewModel noteViewModel,
            SelectNoteDAO selectNoteDAO
    ){
        SelectNoteOutputBoundary selectNoteOutputBoundary = new SelectNotePresenter(noteViewModel, viewManagerModel);
        SelectNoteInputBoundary selectNoteInteractor = new SelectNoteInteractor(selectNoteDAO, selectNoteOutputBoundary);
        return new SelectNoteController(selectNoteInteractor);
    }
}