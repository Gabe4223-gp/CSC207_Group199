package interface_adapter.select_note;
import use_case.select_note.SelectNoteInputBoundary;
import use_case.select_note.SelectNoteInputData;

public class SelectNoteController {
    final SelectNoteInputBoundary selectNoteInteractor;


    public SelectNoteController(SelectNoteInputBoundary selectNoteInteractor) {
        this.selectNoteInteractor = selectNoteInteractor;
    }

    public void selectNote(String filename, Integer index, String username){
        SelectNoteInputData selectNoteInputData = new SelectNoteInputData(filename, index, username);
        selectNoteInteractor.selectNote(selectNoteInputData);
    }
}
