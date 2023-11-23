package use_case.select_note;

/**
 * Select Note Use Case interactor to use the select note data access interface and tell the presenter to prepare success or
 * fail views.
 */
public class SelectNoteInteractor implements SelectNoteInputBoundary{
    final SelectNoteDataAccessInterface selectNoteDataAccessInterface;
    final SelectNoteOutputBoundary selectNotePresenter;

    public SelectNoteInteractor(SelectNoteDataAccessInterface selectNoteDataAccessInterface, SelectNoteOutputBoundary selectNotePresenter) {
        this.selectNoteDataAccessInterface = selectNoteDataAccessInterface;
        this.selectNotePresenter = selectNotePresenter;
    }


    /**
     * Transfer the filename, index and username using the data access object to fetch the text note date,
     * prepare successful view or failed view according to index
     * @param selectNoteInputData The input data from the controller to be used to fetch the file
     */
    @Override
    public void selectNote(SelectNoteInputData selectNoteInputData) {
        String filename = selectNoteInputData.getFilename();
        String username = selectNoteInputData.getUsername();
        Integer index = selectNoteInputData.getIndex();
        if (index == -1){
            selectNotePresenter.prepareSelectFailView("Please select a note");
        }
        else {
            String note_data = selectNoteDataAccessInterface.getSelectedNote(filename, index, username);
            SelectNoteOutputData selectNoteOutputData = new SelectNoteOutputData(filename, note_data);
            selectNotePresenter.prepareSelectSuccessfulView(selectNoteOutputData);
        }
    }
}
