package view;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notes";
    private final NoteViewModel noteViewModel;
    final JTextField filenameInput = new JTextField(15);
    private JTextArea textArea;
    final JButton save;
    final JButton newBtn;
    final JButton deleteBtn;
    private final NoteController noteController;

    public NoteView(NoteViewModel noteViewModel, NoteController noteController) {
        this.noteViewModel = noteViewModel;
        this.noteController = noteController;

        noteViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(noteViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel filenameInfo = new LabelTextPanel(
                new JLabel(noteViewModel.FILENAME_LBL), filenameInput
        );
        JPanel buttons = new JPanel();
        save = new JButton(noteViewModel.SAVE_BTN_LBL);
        newBtn = new JButton(noteViewModel.NEW_BTN_LBL);
        deleteBtn = new JButton(noteViewModel.DELETE_BTN_LBL);

        /*
        * Bind action listeners to the buttons
        * */
        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(save)){
                            NoteState currentNoteState = noteViewModel.getNoteState();
                            currentNoteState.setFilename(filenameInput.getText());
                            currentNoteState.setFile_txt(textArea.getText());
                            //TODO: Continue here
                        }
                    }
                }
        );


        buttons.add(save);
        buttons.add(newBtn);
        buttons.add(deleteBtn);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        JPanel fileList = getBtnLst(noteViewModel.getNoteState().getUserFiles());

        textArea = new JTextArea("Your Text Here");
        panel2.add(fileList);
        panel2.add(textArea);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        this.add(title);
        this.add(filenameInfo);
        this.add(buttons);
        this.add(panel2);

    }

    @NotNull
    private static JPanel getBtnLst(ArrayList<String> files) {
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("asf");
        fileList.add("hello");
        fileList.add("oh my");
        DefaultListModel<String> jListModel = new DefaultListModel<>();
        for(String s: fileList){
            jListModel.addElement(s);
        }
        JList<String> jList = new JList<>(jListModel);
        JPanel retPanel = new JPanel();
        retPanel.add(jList);
        return retPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
