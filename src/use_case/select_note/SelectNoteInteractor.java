package use_case.select_note;


public class SelectNoteInteractor implements SelectNoteInputBoundary{
    final SelectNoteDataAccessInterface selectNoteDataAccessInterface;
    final SelectNoteOutputBoundary selectNotePresenter;

    public SelectNoteInteractor(SelectNoteDataAccessInterface selectNoteDataAccessInterface, SelectNoteOutputBoundary selectNotePresenter) {
        this.selectNoteDataAccessInterface = selectNoteDataAccessInterface;
        this.selectNotePresenter = selectNotePresenter;
    }


    @Override
    public void selectNote(SelectNoteInputData selectNoteInputData) {
        String filename = selectNoteInputData.getFilename();
        String username = selectNoteInputData.getUsername();
        Integer index = selectNoteInputData.getIndex();
        String note_data = selectNoteDataAccessInterface.getSelectedNote(filename, index, username);
        if (note_data.isEmpty()){
            selectNotePresenter.prepareSelectFailView("Fails to get data");
        }
        else {
            SelectNoteOutputData selectNoteOutputData = new SelectNoteOutputData(filename, note_data);
            selectNotePresenter.prepareSelectSuccessfulView(selectNoteOutputData);
        }
    }
}
