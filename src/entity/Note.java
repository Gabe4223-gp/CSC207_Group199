package entity;

import java.time.LocalDateTime;

public abstract class Note {

    private String fileName;

    private LocalDateTime createdTime;

    private String username;

    public Note(String fileName, LocalDateTime createdTime, String createdBy){
        this.fileName = fileName;
        this.createdTime = createdTime;
        this.username = createdBy;
    }
    public void setFileName(String newFileName) {
        this.fileName = newFileName;
    }

    public void setCreatedTime(LocalDateTime newCreatedTime) {
        this.createdTime = newCreatedTime;
    }

    public String getFileName() {
        return this.fileName;
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public String getCreatedUser() {
        return this.username;
    }

}
