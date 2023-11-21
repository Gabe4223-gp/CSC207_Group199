package interface_adapter.delete_note;

import use_case.delete_note.DeleteNoteInputBoundary;
import use_case.delete_note.DeleteNoteInputData;

public class DeleteNoteController {
    private DeleteNoteInputBoundary deleteNoteInteractor;
    public DeleteNoteController(DeleteNoteInputBoundary deleteNoteInputBoundary){
        this.deleteNoteInteractor = deleteNoteInputBoundary;
    }
    public void executeDeleteNote(String username, String filename){
        DeleteNoteInputData deleteNoteInputData = new DeleteNoteInputData(username, filename);
        this.deleteNoteInteractor.executeDeleteNote(deleteNoteInputData);
    }
}
