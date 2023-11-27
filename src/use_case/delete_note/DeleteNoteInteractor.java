package use_case.delete_note;

import use_case.NoteOutputData;
import java.util.ArrayList;

public class DeleteNoteInteractor implements DeleteNoteInputBoundary {
    private final DeleteNoteOutputBoundary deleteNotePresenter;
    private final DeleteNoteDataAccessInterface deleteNoteDAO;
    private final DeleteFileAPIDataAccessInterface deleteFileAPIDAO;

    public DeleteNoteInteractor(DeleteNoteOutputBoundary deleteNotePresenter, DeleteNoteDataAccessInterface deleteNoteDAO,
                                DeleteFileAPIDataAccessInterface deleteFileAPIDAO){
        this.deleteNoteDAO = deleteNoteDAO;
        this.deleteNotePresenter = deleteNotePresenter;
        this.deleteFileAPIDAO = deleteFileAPIDAO;
    }

    @Override
    public void executeDeleteNote(DeleteNoteInputData deleteNoteInputData) {
        boolean deleteSuccess = this.deleteNoteDAO.deleteNote(deleteNoteInputData.getUsername(),
                deleteNoteInputData.getFilename());
        boolean deleteAPISuccess = this.deleteFileAPIDAO.deleteUserFile(deleteNoteInputData.getUsername(),
                deleteNoteInputData.getFilename());
        if(deleteSuccess && deleteAPISuccess){
            ArrayList<String> userFiles = this.deleteNoteDAO.getUserFiles(deleteNoteInputData.getUsername());
            NoteOutputData noteOutputData;
            if(!userFiles.isEmpty()){
                String fileTxt = this.deleteNoteDAO.getFileData(deleteNoteInputData.getUsername(), userFiles.get(0));
                noteOutputData = new NoteOutputData(userFiles.get(0),
                        fileTxt,
                        userFiles, deleteNoteInputData.getUsername(), false);
            }else {
                noteOutputData = new NoteOutputData("",
                        "",
                        new ArrayList<>(), deleteNoteInputData.getUsername(), false);
            }
            this.deleteNotePresenter.prepareDeleteNoteSuccessView(noteOutputData);
        }else {
            this.deleteNotePresenter.prepareDeleteNoteFailView("Failed To delete:" + deleteNoteInputData.getFilename());
        }
    }
}
