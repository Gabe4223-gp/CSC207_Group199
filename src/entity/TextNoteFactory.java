package entity;

import java.time.LocalDateTime;

public class TextNoteFactory {

    private TextNoteFactory(){}
    public static TextNote createTextNote(String fileName, LocalDateTime createdTime, String username, String fileTxt){
        return new TextNote(fileName,createdTime,username,fileTxt);
    }
}