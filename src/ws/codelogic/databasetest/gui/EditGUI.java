package ws.codelogic.databasetest.gui;

import javax.swing.*;

public class EditGUI {

    public void makeWindow(){

        SwingUtilities.invokeLater(listWindow());

    }

    private Runnable listWindow() {
        return new Runnable(){
            public void run(){
                JFrame frame = new EditFrame("Note Access");
                frame.setSize(300, 500);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        };
    }
}
