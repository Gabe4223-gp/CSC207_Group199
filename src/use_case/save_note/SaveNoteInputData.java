package use_case.save_note;

import java.time.LocalDateTime;

/**
 * The data storage class for note view that saves the current data from the note view.
 */
public class SaveNoteInputData {

    final private String username;
    final private String noteData;
    final private String fileName;
    final private LocalDateTime createdTime;

    public SaveNoteInputData(String username, String noteData, String fileName, LocalDateTime createdTime) {
        this.username = username;
        this.noteData = noteData;
        this.fileName = fileName;
        this.createdTime = createdTime;
    }

    public String getUsername() {
        return username;
    }

    public String getNoteData() {
        return noteData;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

}
