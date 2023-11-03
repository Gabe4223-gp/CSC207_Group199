package use_case.save_note;

public class SaveNoteOutputData {
    private final String fileName;
    private boolean useCaseFailed;

    public SaveNoteOutputData(String fileName, boolean useCaseFailed) {
        this.fileName = fileName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFileName() {
        return fileName;
    }
}
