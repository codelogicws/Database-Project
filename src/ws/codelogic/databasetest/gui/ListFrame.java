package ws.codelogic.databasetest.gui;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.controller.CreateNew;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListFrame extends JFrame {

    private Container mainContainer;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private GridBagConstraints gbc;
    private TheHandler handler;
    private PersistentData pd;
    private ArrayList<JButton> noteButtons;
    private ArrayList<JButton> deleteButtons;
    private JButton createButton;

    public ListFrame(String title) {
        super(title);
        setUp();
        createBasicButtons();
        createButtons(pd.getTitles(), gbc);
        mainPanel.setBounds(5,5,300, 200);
        scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane, BorderLayout.CENTER);

    }

    private void createBasicButtons() {
        createButton = new JButton("Create New Note");
        mainPanel.add(createButton, gbc);
        createButton.addActionListener(handler);
        gbc.gridy++;
    }

    private void setUp() {
        handler = new TheHandler();
        pd = MySQLHome.createHomeSQLDatabase();
        noteButtons = new ArrayList<>();
        deleteButtons = new ArrayList<>();
        setLayout(new BorderLayout());
        mainContainer = getContentPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;
    }

    private void createButtons(String[] buttons, GridBagConstraints gbc){
        for(int i=0;i<buttons.length;i++){
            createNoteButton(buttons[i], gbc);
        }
    }

    private void createNoteButton(String name, GridBagConstraints gbc) {
        gbc.gridx = 0;
        JButton button = new JButton(name);
        mainPanel.add(button, gbc);
        button.addActionListener(handler);
        noteButtons.add(button);

        gbc.gridx = 1;
        JButton delete = new JButton("delete");
        mainPanel.add(delete, gbc);
        delete.addActionListener(handler);
        deleteButtons.add(delete);

        gbc.gridy++;
    }

    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==createButton){
                CreateNew createNew = new CreateNew();
                createNew.createNewWindow();
            }
            for(int i=0;i< noteButtons.size();i++){
                if(e.getSource()== noteButtons.get(i)){
                    System.out.println("Debug-TheHandler: " + i);
                    Note note = pd.getNote(i);
                    EditGUI editGUI = new EditGUI();
//                    editGUI.makeWindow(note.getTitle(), note.getContent());
                }
            }
            for(int i=0;i<deleteButtons.size();i++){
                if(e.getSource()== deleteButtons.get(i)){
                    pd.removeNote(i);
                }
            }
        }
    }
}
