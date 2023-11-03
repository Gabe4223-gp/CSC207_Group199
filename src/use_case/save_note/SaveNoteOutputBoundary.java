package use_case.save_note;

public interface SaveNoteOutputBoundary {
    void prepareSuccessView(SaveNoteOutputData fileName);
    void prepareFailView(String error);
}
