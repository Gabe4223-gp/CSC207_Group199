package app;

import data_access.SaveNoteDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import interface_adapter.note.SaveNoteController;
import use_case.save_note.SaveNoteInputBoundary;
import use_case.save_note.SaveNoteInteractor;
import use_case.save_note.SaveNoteOutputBoundary;
import view.NoteView;

public class NotesUseCaseFactory {
    private NotesUseCaseFactory(){}

    public static NoteView createNoteView(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel, SaveNoteDAO saveNoteDAO){
        return new NoteView(noteViewModel, createSaveNoteUseCase(viewManagerModel, noteViewModel,saveNoteDAO));
    }

    private static SaveNoteController createSaveNoteUseCase(
            ViewManagerModel viewManagerModel,
            NoteViewModel noteViewModel,
            SaveNoteDAO saveNoteDAO
    ){
        SaveNoteOutputBoundary saveNoteOutputBoundary = new NotePresenter(noteViewModel,viewManagerModel);
        SaveNoteInputBoundary saveNoteInteractor = new SaveNoteInteractor(saveNoteOutputBoundary, saveNoteDAO);
        return new SaveNoteController(saveNoteInteractor);
    }
}