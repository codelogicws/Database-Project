package ws.codelogic.databasetest.gui;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.controller.CreateNew;
import ws.codelogic.databasetest.gui.controller.EditNote;
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

    public ListFrame() {
        super("Database Note Access");
        oneTimeSetUp();
        refresh();
    }

    private void allButtonCreation() {
        createBasicButtons();
        createButtons(pd.getTitles(), gbc);
    }

    private void oneTimeSetUp() {
        handler = new TheHandler();
        pd = MySQLHome.createHomeSQLDatabase();
    }

    private void createBasicButtons() {
        createButton = new JButton("Create New Note");
        mainContainer.add(createButton, BorderLayout.SOUTH);
        createButton.addActionListener(handler);
        gbc.gridy++;
    }

    public void refresh() {
        removeOldScrollPane();
        setLayout(new BorderLayout());
        mainContainer = getContentPane();
        noteButtons = new ArrayList<>();
        deleteButtons = new ArrayList<>();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;
        allButtonCreation();
        mainPanel.setBounds(5,5,300, 200);
        scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }

    private void removeOldScrollPane() {
        if(scrollPane != null){
            remove(scrollPane);
        }
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
                    Note note = pd.getNote(i);
                    EditNote editNote = new EditNote(i, note);
                    editNote.createNewWindow();
                }
            }
            for(int i=0;i<deleteButtons.size();i++){
                if(e.getSource()== deleteButtons.get(i)){
                    pd.removeNote(i);
                    ListGUI.listSingleton().update();
                }
            }
        }
    }
}
