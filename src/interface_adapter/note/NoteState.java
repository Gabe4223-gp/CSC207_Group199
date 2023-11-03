package interface_adapter.note;

import java.util.ArrayList;

public class NoteState {
    private String username = "";
    private String filename = "";
    private String file_txt = "";
    private ArrayList<String> userFiles = new ArrayList<>();

    public NoteState(NoteState copy){
        this.username = copy.username;
        this.filename = copy.filename;
        this.file_txt = copy.file_txt;
        this.userFiles = copy.userFiles;
    }

    public NoteState(){}

    public String getFilename() {
        return filename;
    }

    public String getFile_txt() {
        return file_txt;
    }

    public ArrayList<String> getUserFiles() {
        return userFiles;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFile_txt(String file_txt) {
        this.file_txt = file_txt;
    }

    public void setUserFiles(ArrayList<String> userFiles) {
        this.userFiles = userFiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
