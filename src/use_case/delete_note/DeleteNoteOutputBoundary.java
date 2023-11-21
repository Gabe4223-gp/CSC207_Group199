package use_case.delete_note;

import use_case.NoteOutputData;

public interface DeleteNoteOutputBoundary {
    void prepareDeleteNoteSuccessView(NoteOutputData noteOutputData);
    void prepareDeleteNoteFailView(String error);
}
