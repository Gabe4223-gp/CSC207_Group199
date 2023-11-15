package data_access;

import entity.TextNote;
import use_case.save_note.SaveNoteDataAccessInterface;
//TODO: implement file writing in this class
/**
 * Class to write the given TextNote to a file
 */
public class SaveNoteDAO implements SaveNoteDataAccessInterface {

    @Override
    public boolean saveNote(TextNote textNote) {
        return false;
    }
}
