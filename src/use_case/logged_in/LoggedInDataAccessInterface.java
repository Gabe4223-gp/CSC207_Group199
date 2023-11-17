package use_case.logged_in;
import entity.TextNote;
import entity.User;
import java.util.ArrayList;

public interface LoggedInDataAccessInterface {
    ArrayList<String> getUserFiles(String Username);
    TextNote getTextNote(String fileName, String username);
}
