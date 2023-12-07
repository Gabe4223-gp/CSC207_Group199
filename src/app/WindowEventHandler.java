package app;

import data_access.file_read_write.DeleteNoteWriterDAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLOutput;

public class WindowEventHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        DeleteNoteWriterDAO deleteNoteWriterDAO = new DeleteNoteWriterDAO();
        deleteNoteWriterDAO.deleteUserFolder();
        System.exit(0);
    }
}
