package data_access;

import java.io.*;

public class TextNoteWriter {
    private final File dir;
    private final String ROOT_DIR = "User Data\\";
    public TextNoteWriter(){
        dir = new File(ROOT_DIR);
        dir.mkdirs();
    }

    public boolean writeDataToFile(String filename,String Username, String data) throws IOException {
        File thisDir = new File(ROOT_DIR + Username +"\\");
        thisDir.mkdirs();
        File thisFile = new File(ROOT_DIR + Username +"\\" + filename + ".txt");
        Writer fileWriter = new FileWriter(thisFile);
        fileWriter.write(data);
        fileWriter.close();
        return true;
    }
}
