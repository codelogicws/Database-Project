package ws.codelogic.databasetest.gui;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {
    private final Container mainContainer;
    private final String note;
    private String titleOfNote;

    public EditFrame(String title, String titleOfNote, String note) {
        super(title);
        this.titleOfNote = titleOfNote;
        this.note = note;
        setLayout(new BorderLayout());
        mainContainer = getContentPane();

        createTextFields();
    }

    private void createTextFields() {
        JTextField textField = new JTextField(titleOfNote);
        JTextArea textArea = new JTextArea(note);
        JButton save = new JButton("Save");

        mainContainer.add(textField, BorderLayout.NORTH);
        mainContainer.add(textArea, BorderLayout.CENTER);
        mainContainer.add(save, BorderLayout.SOUTH);
    }


}
