package use_case.SaveNote;

import java.time.LocalDateTime;
import java.util.SplittableRandom;

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
