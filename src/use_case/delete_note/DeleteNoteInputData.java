package use_case.delete_note;

public class DeleteNoteInputData {
    private String username;
    private String filename;

    public DeleteNoteInputData(String username, String filename){
        this.username = username;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getUsername() {
        return username;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
