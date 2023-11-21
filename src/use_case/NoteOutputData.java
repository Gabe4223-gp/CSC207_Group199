package use_case;

import java.util.ArrayList;

public class NoteOutputData {
    private final String fileName;
    private final String file_txt;
    private final ArrayList<String> userFiles;
    private final String username;
    private boolean useCaseFailed;

    public NoteOutputData(String fileName, String file_txt, ArrayList<String> userFiles, String username, boolean useCaseFailed) {
        this.fileName = fileName;
        this.file_txt = file_txt;
        this.userFiles = userFiles;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFileName() {
        return fileName;
    }
    public String getFile_txt() {
        return file_txt;
    }
    public ArrayList<String> getUserFiles() {
        return userFiles;
    }
    public String getUsername() {
        return username;
    }
}
