package interface_adapter.note;

import use_case.save_note.SaveNoteInputData;
import use_case.save_note.SaveNoteInputBoundary;

import java.time.LocalDateTime;

public class SaveNoteController {
    final SaveNoteInputBoundary saveNoteInteractor;
    public SaveNoteController(SaveNoteInputBoundary saveNoteInteractor) {
        this.saveNoteInteractor =saveNoteInteractor;
    }

    public void executeSaveNote(String username, String noteData, String fileName, LocalDateTime createdTime) {
        SaveNoteInputData saveNoteInputData = new SaveNoteInputData(username, noteData, fileName, createdTime);
        saveNoteInteractor.saveFile(saveNoteInputData);
    }
}