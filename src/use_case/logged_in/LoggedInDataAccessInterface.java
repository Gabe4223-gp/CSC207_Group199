package use_case.logged_in;
import entity.TextNote;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;

public interface LoggedInDataAccessInterface {
    ArrayList<String> getUserFiles(String Username);
    ArrayList<String> downloadUserFile(String username);
    ArrayList<String> listContentsUserFolder(String username);
    TextNote getTextNote(String fileName, String username);
    void writeDataToFile(String filename, String username, String filedata);
}
