package data_access;

import entity.TextNote;
import use_case.save_note.SaveNoteDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
//TODO: implement file writing in this class
/**
 * Class to write the given TextNote to a file
 */
public class SaveNoteDAO implements SaveNoteDataAccessInterface {

    private final TextNoteWriter textNoteWriter;

    public SaveNoteDAO(TextNoteWriter textNoteWriter){
        this.textNoteWriter = textNoteWriter;
    }
    @Override
    public boolean saveNote(TextNote textNote) {
        try
        {
            this.textNoteWriter.writeDataToFile(textNote.getFileName(),
                    textNote.getCreatedUser(),
                    textNote.getFileTxt());
            return true;
        }catch (IOException e){
            return false;
        }
    }

    @Override
    public ArrayList<String> getAllUserFiles(String Username) {
        return new ArrayList<>();
    }
}
