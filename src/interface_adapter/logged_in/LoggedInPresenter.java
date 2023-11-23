package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.draw_note.DrawNoteState;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import use_case.logged_in.LoggedInOutputBoundary;

import java.util.ArrayList;

public class LoggedInPresenter implements LoggedInOutputBoundary {

    private final DrawNoteViewModel drawNoteViewModel;

    private final NoteViewModel textNoteViewModel;

    private final ViewManagerModel viewManagerModel;


    public LoggedInPresenter(DrawNoteViewModel drawNoteViewModel, NoteViewModel textNoteViewModel,
                             ViewManagerModel viewManagerModel){
        this.drawNoteViewModel = drawNoteViewModel;
        this.textNoteViewModel = textNoteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareTextNoteView(String username, String currentFilename, String fileTxt, ArrayList<String> files) {
        NoteState textNoteState = textNoteViewModel.getNoteState();
        textNoteState.setUsername(username);
        textNoteState.setFilename(currentFilename);
        textNoteState.setFileTxt(fileTxt);
        textNoteState.setUserFiles(files);
        this.textNoteViewModel.setNoteState(textNoteState);
        this.textNoteViewModel.firePropertyChange();
        this.viewManagerModel.setActiveView(textNoteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareDrawNoteView() {
        DrawNoteState drawNoteState = drawNoteViewModel.getDrawState();
        this.drawNoteViewModel.setDrawState(drawNoteState);
        this.drawNoteViewModel.firePropertyChange();

        this.viewManagerModel.setActiveView(drawNoteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
