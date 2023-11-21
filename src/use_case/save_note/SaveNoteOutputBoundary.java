package use_case.save_note;

import use_case.NoteOutputData;

public interface SaveNoteOutputBoundary {
    void prepareSaveNoteSuccessView(NoteOutputData noteOutputData);
    void prepareSaveNoteFailView(String error);
}
