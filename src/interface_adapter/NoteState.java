package interface_adapter;

import java.util.ArrayList;

public class NoteState {
    private String username = "";
    private String filename = "";
    private String fileTxt = "";
    private String error = "";
    private ArrayList<String> userFiles = new ArrayList<>();

    public NoteState(NoteState copy){
        this.username = copy.username;
        this.filename = copy.filename;
        this.fileTxt = copy.fileTxt;
        this.userFiles = copy.userFiles;
    }

    public NoteState(){}

    public String getFilename() {
        return filename;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public ArrayList<String> getUserFiles() {
        return userFiles;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
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

    public void setError(String error){this.error = error;}

    public String getError(){return this.error;}
}
