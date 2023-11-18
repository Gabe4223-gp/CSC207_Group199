package data_access.file_read_write;

import data_access.file_read_write.FileAccessDAO;

import java.io.*;

public class TextNoteWriterDAO extends FileAccessDAO {
    public TextNoteWriterDAO(){
        super();
    }

    public void writeDataToFile(String filename, String Username, String data) throws IOException {
        File thisDir = new File(ROOT_DIR + Username +"\\");
        thisDir.mkdirs();
        File thisFile = new File(ROOT_DIR + Username +"\\" + filename + ".txt");
        Writer fileWriter = new FileWriter(thisFile);
        fileWriter.write(data);
        fileWriter.close();
    }
}
