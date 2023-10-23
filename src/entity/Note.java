package entity;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public abstract class Note {

    private String fileName;

    private LocalDateTime createdTime;

    private User createdUser;

    public Note(String fileName, LocalDateTime createdTime, User createdUser) {

        this.fileName = fileName;
        this.createdTime = createdTime;
        this. createdUser = createdUser;

    }

    public void setFileName(String newFileName) {
        fileName = newFileName;
    }

    public void setCreatedTime(LocalDateTime newCreatedTime) {
        createdTime = newCreatedTime;
    }

    public void setCreatedUser(User newCreatedUser) {
        createdUser = newCreatedUser;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public User getCreatedUser() {
        return this.createdUser;
    }

}
