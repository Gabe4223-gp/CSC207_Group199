package data_access.file_read_write;

import data_access.file_read_write.FileAccessDAO;

import java.io.*;
import java.util.logging.Level;

public class TextNoteWriterDAO extends FileAccessDAO {
    public TextNoteWriterDAO(){
        super();
    }

    public void writeDataToFile(String filename, String username, String data) throws IOException {
        File thisDir = new File(ROOT_DIR + username + File.separator);
        thisDir.mkdirs();
        File thisFile = new File(ROOT_DIR + username + File.separator + filename + ".txt");
        try (FileWriter fileWriter = new FileWriter(thisFile)) {
            fileWriter.write(data);
        } catch (Exception e) {
            this.logger.log(Level.WARNING, e.getMessage());
        }
    }
}
