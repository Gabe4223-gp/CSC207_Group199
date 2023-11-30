package view;

import interface_adapter.delete_note.DeleteNoteController;
import interface_adapter.NoteState;
import interface_adapter.NoteViewModel;
import interface_adapter.save_note.SaveNoteController;
import interface_adapter.select_note.SelectNoteController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "notes";
    private final NoteViewModel noteViewModel;
    final JTextField filenameInput = new JTextField(15);
    private final JTextArea textArea;
    private final JButton save;
    final JButton newBtn;
    final JButton deleteBtn;
    final JButton select;
    private JList<String> fileList;
    private final JPanel fileListPanel;
    private final SaveNoteController saveNoteController;
    private final DeleteNoteController deleteNoteController;
    private final SelectNoteController selectNoteController;
    public NoteView(NoteViewModel noteViewModel, SaveNoteController saveNoteController, DeleteNoteController deleteNoteController, SelectNoteController selectNoteController) {
        this.noteViewModel = noteViewModel;
        this.saveNoteController = saveNoteController;
        this.deleteNoteController = deleteNoteController;
        this.selectNoteController = selectNoteController;

        noteViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(NoteViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        filenameInput.setText(noteViewModel.getNoteState().getFilename());
        LabelTextPanel filenameInfo = new LabelTextPanel(
                new JLabel(noteViewModel.FILENAME_LBL), filenameInput
        );
        JPanel buttons = new JPanel();
        save = new JButton(noteViewModel.SAVE_BTN_LBL);
        newBtn = new JButton(noteViewModel.NEW_BTN_LBL);
        deleteBtn = new JButton(noteViewModel.DELETE_BTN_LBL);
        select = new JButton(noteViewModel.SELECT_BTN_LBL);

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
                            currentNoteState.setFileTxt(textArea.getText());
                            saveNoteController.executeSaveNote(
                                    currentNoteState.getUsername(),
                                    currentNoteState.getFileTxt(),
                                    currentNoteState.getFilename(),
                                    LocalDateTime.now());
                        }
                    }
                }
        );

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(deleteBtn)){
                    NoteState currentNoteState = noteViewModel.getNoteState();
                    currentNoteState.setFilename(filenameInput.getText());
                    currentNoteState.setFilename(filenameInput.getText());
                    currentNoteState.setFileTxt(textArea.getText());
                    deleteNoteController.executeDeleteNote(
                            currentNoteState.getUsername(),
                            currentNoteState.getFilename()
                    );
                }
            }
        });

        select.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = fileList.getSelectedIndex();
                        String filename = fileList.getSelectedValue();
                        String username = noteViewModel.getNoteState().getUsername();
                        selectNoteController.selectNote(filename, index, username);
                    }
                }
        );

        buttons.add(save);
        buttons.add(newBtn);
        buttons.add(deleteBtn);
        buttons.add(select);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        fileList = getBtnLst(noteViewModel.getNoteState().getUserFiles());
        fileListPanel = new JPanel();
        textArea = new JTextArea(noteViewModel.getNoteState().getFileTxt());
        panel2.add(fileListPanel);
        panel2.add(textArea);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        this.add(title);
        this.add(filenameInfo);
        this.add(buttons);
        this.add(panel2);
        noteViewModel.firePropertyChange();
    }

    @NotNull
    private static JList<String> getBtnLst(ArrayList<String> files) {
        DefaultListModel<String> jListModel = new DefaultListModel<>();
        for(String s: files){
            jListModel.addElement(s);
        }
        return new JList<>(jListModel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // actionPerformed not used for this class
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NoteState state = (NoteState) evt.getNewValue();
        this.fileList = getBtnLst(state.getUserFiles());
        this.fileListPanel.removeAll();
        this.fileListPanel.add(this.fileList);
        this.fileListPanel.updateUI();
        this.textArea.setText(state.getFileTxt());
        this.filenameInput.setText(state.getFilename());

        if (!state.getError().isEmpty()){
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }

    public JButton getSave() {
        return save;
    }
}
