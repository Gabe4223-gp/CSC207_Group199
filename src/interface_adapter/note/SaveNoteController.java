package interface_adapter.save_note;

import use_case.save_note.SaveNoteInputData;
import use_case.save_note.SaveNoteBoundary;
public class SaveNoteController {
    final SaveNoteInputBoundary saveNoteInteractor;
    public SaveNoteController(SaveNoteInputBoundary saveNoteInteractor) {
        this.saveNoteInteractor =saveNoteInteractor;
    }

    public void execute(String username, String noteData, String fileName, LocalDateTime createdTime) {
        SaveNoteInputData saveNoteInputData = new SaveNoteInputeData(username, noteData, fileName, createdTime);
        saveNoteInteractor.execute(saveNoteInputData);
    }
}