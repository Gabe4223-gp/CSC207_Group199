package view;

import interface_adapter.login.LoginState;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import interface_adapter.note.SaveNoteController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notes";
    private final NoteViewModel noteViewModel;
    final JTextField filenameInput = new JTextField(15);
    private JTextArea textArea;
    final JButton save;
    final JButton newBtn;
    final JButton deleteBtn;
    private JList fileList;
    private JPanel fileListPanel;
    private final SaveNoteController saveNoteController;

    public NoteView(NoteViewModel noteViewModel, SaveNoteController saveNoteController) {
        this.noteViewModel = noteViewModel;
        this.saveNoteController = saveNoteController;

        noteViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(noteViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        filenameInput.setText(noteViewModel.getNoteState().getFilename());
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
                            saveNoteController.executeSaveNote(
                                    currentNoteState.getUsername(),
                                    currentNoteState.getFile_txt(),
                                    currentNoteState.getFilename(),
                                    LocalDateTime.now());
                        }
                    }
                }
        );


        buttons.add(save);
        buttons.add(newBtn);
        buttons.add(deleteBtn);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        fileList = getBtnLst(noteViewModel.getNoteState().getUserFiles());
        fileListPanel = new JPanel();
        textArea = new JTextArea(noteViewModel.getNoteState().getFile_txt());
        panel2.add(fileListPanel);
        panel2.add(textArea);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        this.add(title);
        this.add(filenameInfo);
        this.add(buttons);
        this.add(panel2);
    }

    @NotNull
    private static JList getBtnLst(ArrayList<String> files) {
        DefaultListModel<String> jListModel = new DefaultListModel<>();
        for(String s: files){
            jListModel.addElement(s);
        }
        JList<String> jList = new JList<>(jListModel);
        return jList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NoteState state = (NoteState) evt.getNewValue();
         this.fileList = getBtnLst(state.getUserFiles());
         fileListPanel.removeAll();
         fileListPanel.add(this.fileList);
         fileListPanel.updateUI();
         this.textArea.setText(state.getFile_txt());
         this.filenameInput.setText(state.getFilename());
         this.validate();
         this.repaint();
    }
}
