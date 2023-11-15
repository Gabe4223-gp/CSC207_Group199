package use_case.save_note;

public interface SaveNoteOutputBoundary {
    void prepareSaveNoteSuccessView(SaveNoteOutputData fileName);
    void prepareSaveNoteFailView(String error);
}
