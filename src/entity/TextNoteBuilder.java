package entity;

import java.time.LocalDateTime;

public class TextNoteBuilder {

    private TextNoteBuilder(){}
    public static TextNote createTextNote(String fileName, LocalDateTime createdTime, String username, String fileTxt){
        return new TextNote(fileName,createdTime,username,fileTxt);
    }
}