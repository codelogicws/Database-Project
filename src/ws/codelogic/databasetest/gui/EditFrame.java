package ws.codelogic.databasetest.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditFrame extends JFrame {
    private Container mainContainer;
    private final String note;
    private String titleOfNote;
    private JTextField textField;
    private JTextArea textArea;
    private JButton save;
    private ActionListener handler;
    private JScrollPane testAreaScroll;

    public EditFrame(String title, String titleOfNote, String note, ActionListener handler) {
        super(title);
        this.handler = handler;
        this.titleOfNote = titleOfNote;
        this.note = note;
        initFrame();
        createFields();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        mainContainer = getContentPane();
    }

    private void createFields() {
        initComponents();
        save.addActionListener(handler);
        addAllToContainer();
    }

    private void initComponents() {
        textField = new JTextField(titleOfNote);
        textArea = new JTextArea(note);
        save = new JButton("Save");
        testAreaScroll = new JScrollPane(textArea);
    }

    private void addAllToContainer() {
        mainContainer.add(textField, BorderLayout.NORTH);
        mainContainer.add(testAreaScroll, BorderLayout.CENTER);
        mainContainer.add(save, BorderLayout.SOUTH);
    }

    public String getNote() {
        return textArea.getText();
    }

    public String getNoteTitle() {
        return textField.getText();
    }
}
