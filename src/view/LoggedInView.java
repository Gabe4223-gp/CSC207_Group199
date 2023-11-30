package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    final JButton noteBtn;
    final JButton drawBtn;


    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController){

        this.loggedInViewModel = loggedInViewModel;
        this.loggedInController = loggedInController;

        JLabel title = new JLabel(LoggedInViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        noteBtn = new JButton(LoggedInViewModel.TEXT_NOTE_LABEL);
        drawBtn = new JButton(LoggedInViewModel.DRAW_NOTE_LABEL);
        buttons.add(noteBtn);
        buttons.add(drawBtn);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        noteBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            loggedInController.textNoteExecute(loggedInViewModel.getState().getUsername());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("note btn clicked");}
                }
        );
        drawBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loggedInController.drawNoteExecute();
                        System.out.println("draw btn clicked");
                    }
                }
        );

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
