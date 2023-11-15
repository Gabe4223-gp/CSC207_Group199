package entity;

import java.time.LocalDateTime;

public class TextNote extends Note{
    private String fileTxt = "";
    public TextNote(String fileName, LocalDateTime createdTime, String createdBy, String fileTxt) {
        super(fileName, createdTime, createdBy);
        this.fileTxt = fileTxt;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
    }
}
