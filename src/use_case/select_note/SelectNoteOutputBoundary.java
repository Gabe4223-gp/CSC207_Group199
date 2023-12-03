package use_case.select_note;

import use_case.NoteOutputData;
/**
 *Interface used to handle with output data
 */
public interface SelectNoteOutputBoundary {

    void prepareSelectFailView(String error);

    void prepareSelectSuccessfulView(NoteOutputData noteOutputData);
}
