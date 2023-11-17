package data_access.file_read_write;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FileAccessDAO {
    private final File dir;
    public String ROOT_DIR = "User Data\\";
    public FileAccessDAO(){
        dir = new File(ROOT_DIR);
        dir.mkdirs();
    }
    public String getFileData(String username, String filename) throws IOException {
        String path = ROOT_DIR+username+"\\"+filename;
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
