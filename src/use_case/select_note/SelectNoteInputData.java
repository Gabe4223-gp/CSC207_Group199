package use_case.select_note;

/**
 * The data storage class for note view that saves the selected data from the note view.
 */
public class SelectNoteInputData {
    final private String filename;
    final private String username;

    final private Integer index;

    public SelectNoteInputData(String filename, Integer index, String username) {
        this.filename = filename;
        this.username = username;
        this.index = index;
    }

    public String getFilename(){return this.filename;}

    public String getUsername(){return this.username;}

    public Integer getIndex(){return this.index;}
}
