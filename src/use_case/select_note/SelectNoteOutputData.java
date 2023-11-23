package use_case.select_note;

public class SelectNoteOutputData {
    final private String filename;
    final private String note_data;


    public SelectNoteOutputData(String filename, String note_data) {
        this.filename = filename;
        this.note_data = note_data;
    }

    public String getFilename(){return this.filename;}

    public String getNoteData(){return this.note_data;}
}
