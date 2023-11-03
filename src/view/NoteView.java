package view;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notes";
    private final NoteViewModel noteViewModel;
    final JTextField filenameInput = new JTextField(15);
    final JTextArea textArea = new JTextArea();
    final JButton save;
    final JButton newBtn;
    private final NoteController noteController;

    public NoteView(NoteViewModel noteViewModel, NoteController noteController) {
        this.noteViewModel = noteViewModel;
        this.noteController = noteController;

        JLabel title = new JLabel(noteViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel filenameInfo = new LabelTextPanel(
                new JLabel(noteViewModel.FILENAME_LBL), filenameInput
        );
        JPanel buttons = new JPanel();
        save = new JButton(noteViewModel.SAVE_BTN_LBL);
        newBtn = new JButton(noteViewModel.NEW_BTN_LBL);
        buttons.add(save);
        buttons.add(newBtn);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
