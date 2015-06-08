package ws.codelogic.databasetest.gui;

import ws.codelogic.databasetest.gui.controller.CreateNew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditFrame extends JFrame {
    private final Container mainContainer;
    private final String note;
    private String titleOfNote;
    private JTextField textField;
    private JTextArea textArea;
    private JButton save;
    private ActionListener handler;

    public EditFrame(String title, String titleOfNote, String note, ActionListener handler) {
        super(title);
        this.handler = handler;
        this.titleOfNote = titleOfNote;
        this.note = note;
        setLayout(new BorderLayout());
        mainContainer = getContentPane();

        createTextFields();
    }

    private void createTextFields() {
        textField = new JTextField(titleOfNote);
        textArea = new JTextArea(note);
        save = new JButton("Save");
        save.addActionListener(handler);

        mainContainer.add(textField, BorderLayout.NORTH);
        mainContainer.add(textArea, BorderLayout.CENTER);
        mainContainer.add(save, BorderLayout.SOUTH);
    }

    public String getNote() {
        return textArea.getText().toString();
    }

    public String getNoteTitle() {
        return textField.getText().toString();
    }
}
