package data_access.file_read_write;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

public class AllUserFilesDAO extends FileAccessDAO {
    public AllUserFilesDAO(){
        super();
    }

    public ArrayList<String> getAllUserFiles(String username){
        ArrayList<String> retList = new ArrayList<>();
        String path = ROOT_DIR + username;
        if (Files.isDirectory(Paths.get(path))){
            File folder = new File(path);
            if (folder.listFiles() != null){
                for(File f : folder.listFiles()){
                   retList.add(FilenameUtils.removeExtension(f.getName()));
                }
            }
        }else{
            return new ArrayList<>();
        }
        return retList;
    }
}
