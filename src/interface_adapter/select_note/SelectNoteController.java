package interface_adapter.select_note;
import use_case.select_note.SelectNoteInputBoundary;
import use_case.select_note.SelectNoteInputData;
/**
 *A class used by user to make decisions
 */
public class SelectNoteController {
    final SelectNoteInputBoundary selectNoteInteractor;


    public SelectNoteController(SelectNoteInputBoundary selectNoteInteractor) {
        this.selectNoteInteractor = selectNoteInteractor;
    }

    /**
     *Method pass the input data
     * @param filename filename of the note
     * @param username username of the user
     */
    public void selectNote(String filename, Integer index, String username){
        SelectNoteInputData selectNoteInputData = new SelectNoteInputData(filename, index, username);
        selectNoteInteractor.selectNote(selectNoteInputData);
    }
}
