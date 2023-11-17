package view;

import interface_adapter.draw_note.DrawNoteViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DrawNoteView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewTitle = "drawings";
    private final DrawNoteViewModel drawNoteViewModel;


    public DrawNoteView(DrawNoteViewModel drawNoteViewModel) {
        this.drawNoteViewModel = drawNoteViewModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
