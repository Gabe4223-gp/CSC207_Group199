package use_case.select_note;

public interface SelectNoteOutputBoundary {

    void prepareSelectFailView(String error);

    void prepareSelectSuccessfulView(SelectNoteOutputData selectNoteOutputData);
}
