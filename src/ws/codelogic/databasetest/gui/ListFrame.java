package ws.codelogic.databasetest.gui;

import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import javax.swing.*;
import java.awt.*;

public class ListFrame extends JFrame {

    private Container mainContainer;

    public ListFrame(String title) {
        super(title);
        setUp();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = 0;

        PersistentData pd = MySQLHome.createHomeSQLDatabase();
        createButtons(pd.getTitles(), gbc);
    }

    private void setUp() {
        setLayout(new GridBagLayout());
        mainContainer = getContentPane();
    }

    private void createButtons(String[] buttons, GridBagConstraints gbc){
        for(int i=0;i<buttons.length;i++){
            JButton button = new JButton(buttons[i]);
            mainContainer.add(button, gbc);
            gbc.gridy++;
        }
    }
}
