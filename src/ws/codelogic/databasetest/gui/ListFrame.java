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
        handler = new Handler();
        setUpMainContainer();
        SetUpCenterPanel();
        SetUpSouthPanel();
    }

    private void setUpMainContainer() {
        setLayout(new BorderLayout());
        mainContainer = getContentPane();
    }

    private void SetUpCenterPanel() {
        init();
        setUpMainPanel();
        createButtons(pd.getTitles(), gbc);
        setUpScrollPane();
    }

    private void init() {
        noteButtons = new ArrayList<>();
        deleteButtons = new ArrayList<>();
    }

    private void setUpMainPanel() {
        mainPanel = new JPanel();
        setUpGridBagLayout();
        mainPanel.setBounds(5,5,300, 200);
    }

    private void setUpGridBagLayout() {
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;
    }

    private void createButtons(String[] buttons, GridBagConstraints gbc){
        for(int i=0;i<buttons.length;i++){
            createButtonPairForNote(buttons[i], gbc);
        }
    }

    private void setUpScrollPane() {
        if(scrollPane != null)
            remove(scrollPane);
        scrollPane = new JScrollPane(mainPanel);
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }

    private void createButtonPairForNote(String name, GridBagConstraints gbc) {
        addButtonToCluster(name, gbc, 0, noteButtons);
        addButtonToCluster("delete", gbc, 1, deleteButtons);
        gbc.gridy++;
    }

    private void addButtonToCluster(String name, GridBagConstraints gbc, int x, ArrayList<JButton> buttons) {
        gbc.gridx = x;
        JButton button = new JButton(name);
        mainPanel.add(button, gbc);
        button.addActionListener(handler);
        buttons.add(button);
    }

    private void SetUpSouthPanel() {
        if(southPanel != null)
            remove(southPanel);
        createButton = new JButton("Create New Note");
        southPanel = new JPanel();
        southPanel.add(createButton);
        mainContainer.add(southPanel, BorderLayout.SOUTH);
        createButton.addActionListener(handler);
        gbc.gridy++;
    }

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkForNoteCreationClick(e);
            checkForNoteButtonClick(e);
            checkForDeleteButtonClick(e);
        }

        private void checkForNoteButtonClick(ActionEvent e) {
            for(int i=0;i< noteButtons.size();i++){
                if(e.getSource()== noteButtons.get(i)){
                    Note note = pd.getNote(i);
                    EditNote editNote = new EditNote(i, note);
                    editNote.createNewWindow();
                }
            }
        }

        private void checkForDeleteButtonClick(ActionEvent e) {
            for(int i=0;i<deleteButtons.size();i++){
                if(e.getSource()== deleteButtons.get(i)){
                    pd.removeNote(i);
                    ListGUI.listSingleton().update();
                }
            }
        }

        private void checkForNoteCreationClick(ActionEvent e) {
            if(e.getSource()==createButton){
                System.out.println("Debug-Handler: trying to create a new item");
                CreateNew createNew = new CreateNew();
                createNew.createNewWindow();
            }
        }
    }
}
