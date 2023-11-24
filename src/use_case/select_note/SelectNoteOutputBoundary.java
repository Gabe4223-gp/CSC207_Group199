package use_case.select_note;

import use_case.NoteOutputData;

public interface SelectNoteOutputBoundary {

    void prepareSelectFailView(String error);

    void prepareSelectSuccessfulView(NoteOutputData noteOutputData);
}
