package data_access;

import entity.TextNote;
import entity.User;
import use_case.logged_in.LoggedInDataAccessInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LoggedInDAO implements LoggedInDataAccessInterface {

    @Override
    public ArrayList<String> getUserFiles(String Username) {
        //TODO: get all files for the user
        ArrayList<String> files = new ArrayList<>();
        files.add("sdjghfc");
        files.add("sdkjghfh");
        return files;
    }

    @Override
    public TextNote getTextNote(String fileName, String user) {
        //TODO: create a new text note from the given filename
        TextNote retNote = new TextNote(fileName, LocalDateTime.now(), user,"dshmgfcvhdjgfjahgsdf");
        return retNote;
    }
}
