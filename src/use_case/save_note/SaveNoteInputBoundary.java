package use_case.save_note;

public interface SaveNoteInputBoundary {
    void saveFile(SaveNoteInputData saveNoteInputData);
    void makeFile(SaveNoteInputData saveNoteInputData);
}