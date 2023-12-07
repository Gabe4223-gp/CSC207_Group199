package data_access.file_read_write;

import com.mysql.cj.xdevapi.Warning;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class DeleteNoteWriterDAO extends FileAccessDAO {
    public boolean deleteNoteFile(String username, String filename){
        File toDelete = new File(ROOT_DIR + username + File.separator + filename + ".txt");
        return toDelete.delete();
    }
    public void deleteUserFolder(){
        try
        {
            FileUtils.deleteDirectory(new File(ROOT_DIR));
        }catch (IOException e){
            this.logger.log(Level.WARNING, e.getMessage());
        }
    }
}
