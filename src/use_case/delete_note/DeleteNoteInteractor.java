package use_case.delete_note;

import data_access.DeleteNoteDAO;
import use_case.NoteOutputData;

import java.util.ArrayList;

public class DeleteNoteInteractor implements DeleteNoteInputBoundary {
    private DeleteNoteOutputBoundary deleteNotePresenter;
    private DeleteNoteDataAccessInterface deleteNoteDAO;

    public DeleteNoteInteractor(DeleteNoteOutputBoundary deleteNotePresenter, DeleteNoteDataAccessInterface deleteNoteDAO){
        this.deleteNoteDAO = deleteNoteDAO;
        this.deleteNotePresenter = deleteNotePresenter;
    }

    @Override
    public void executeDeleteNote(DeleteNoteInputData deleteNoteInputData) {
        boolean deleteSuccess = this.deleteNoteDAO.deleteNote(deleteNoteInputData.getUsername(),
                deleteNoteInputData.getFilename());
        if(deleteSuccess){
            ArrayList<String> userFiles = this.deleteNoteDAO.getUserFiles(deleteNoteInputData.getUsername());
            String fileTxt = this.deleteNoteDAO.getFileData(deleteNoteInputData.getUsername(), deleteNoteInputData.getFilename());
            NoteOutputData noteOutputData = new NoteOutputData(deleteNoteInputData.getFilename(),
                    fileTxt,
                    userFiles, deleteNoteInputData.getUsername(), false);
            this.deleteNotePresenter.prepareDeleteNoteSuccessView(noteOutputData);
        }else {
            this.deleteNotePresenter.prepareDeleteNoteFailView("Failed To delete:" + deleteNoteInputData.getFilename());
        }
    }
}
