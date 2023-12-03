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

    /**
     *Method used to get filename
     * @return filename
     */
    public String getFilename(){return this.filename;}

    /**
     *Method used to get username
     * @return username
     */
    public String getUsername(){return this.username;}

    /**
     *Method used to get index of the file in the arraylist
     * @return index of the file in the arraylist
     */
    public Integer getIndex(){return this.index;}
}
