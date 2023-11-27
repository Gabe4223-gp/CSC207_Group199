package data_access.file_read_write;

import java.io.File;

public class DeleteNoteWriterDAO extends FileAccessDAO {
    public boolean deleteNoteFile(String username, String filename){
        File toDelete = new File(ROOT_DIR + username + File.separator + filename + ".txt");
        return toDelete.delete();
    }
}
