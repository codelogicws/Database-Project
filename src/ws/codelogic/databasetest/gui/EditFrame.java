package ws.codelogic.databasetest.gui;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {
    private final Container mainContainer;

    public EditFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        mainContainer = getContentPane();

        createTextFields();
    }

    private void createTextFields() {
        JTextArea textArea = new JTextArea();
        JTextField textField = new JTextField();
        JButton save = new JButton("Save");

        mainContainer.add(textField, BorderLayout.NORTH);
        mainContainer.add(textArea, BorderLayout.CENTER);
        mainContainer.add(save, BorderLayout.SOUTH);
    }


}
