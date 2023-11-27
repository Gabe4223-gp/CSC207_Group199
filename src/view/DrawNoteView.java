package view;

import interface_adapter.draw_note.DrawNoteState;
import interface_adapter.draw_note.DrawNoteViewModel;
import interface_adapter.draw_note.SaveDrawingController;
import interface_adapter.draw_note.DrawNoteState;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DrawNoteView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewTitle = "drawings";
    private final DrawNoteViewModel drawNoteViewModel;
    final JButton save;
    final JButton newButton;
    final JButton deleteButton;
    private JList fileList;
    private JPanel fileListPanel;
    private final SaveDrawingController saveDrawingController;

    public DrawNoteView(DrawNoteViewModel drawNoteViewModel, SaveDrawingController saveDrawingController) {
        this.drawNoteViewModel = drawNoteViewModel;
        this.saveDrawingController = saveDrawingController;

        drawNoteViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(drawNoteViewModel.TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        save = new JButton(drawNoteViewModel.SAVE_BTN_LBL);
        newButton = new JButton(drawNoteViewModel.NEW_BTN_LBL);
        deleteButton = new JButton(drawNoteViewModel.DELETE_BTN_LBL);

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)) {
                            DrawNoteState currentNoteState = drawNoteViewModel.getDrawState();
                            //saveDrawingController.execute
                                    LocalDateTime.now();
                        }
                    }
                }
        );

        newButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {}
                }
        );

        deleteButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {}
                }
        );


        buttons.add(save);
        buttons.add(newButton);
        buttons.add(deleteButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        fileList = getBtnLst(drawNoteViewModel.getDrawState().getUserFiles());
        fileListPanel = new JPanel();
        panel2.add(fileListPanel);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(panel2);
        drawNoteViewModel.firePropertyChange();
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
        DrawNoteState state = (DrawNoteState) evt.getNewValue();
        this.fileList = getBtnLst(state.getUserFiles());
        this.fileListPanel.removeAll();
        this.fileListPanel.add(this.fileList);
        this.fileListPanel.updateUI();
    }
}
