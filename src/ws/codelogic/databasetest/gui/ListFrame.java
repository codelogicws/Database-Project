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
    private Handler handler;
    private PersistentData pd;
    private ArrayList<JButton> noteButtons;
    private ArrayList<JButton> deleteButtons;
    private JButton createButton;
    private JPanel southPanel;

    public ListFrame() {
        super("Database Note Access");
        pd = MySQLHome.mySQLHomeSingleton();
        refresh();
    }

    public void refresh() {
        //Action Listener
        handler = new Handler();

        //Main Container
        setLayout(new BorderLayout());
        mainContainer = getContentPane();

        //ScrollPanel
        removeOldComponents();
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

    private void allButtonCreation() {
        createBasicButtons();
        createButtons(pd.getTitles(), gbc);
    }

    private void createBasicButtons() {
        if(southPanel != null)
            remove(southPanel);
        createButton = new JButton("Create New Note");
        southPanel = new JPanel();
        southPanel.add(createButton);
        mainContainer.add(southPanel, BorderLayout.SOUTH);
        createButton.addActionListener(handler);
        gbc.gridy++;
    }

    private void removeOldComponents() {
        if(scrollPane != null)
            remove(scrollPane);
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

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==createButton){
                System.out.println("Debug-Handler: trying to create a new item");
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
