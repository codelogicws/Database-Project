package ws.codelogic.databasetest.gui;

import javax.swing.*;

public class ListGUI {

    public void makeWindow(){

        SwingUtilities.invokeLater(listWindow());

    }

    private Runnable listWindow() {
        return new Runnable(){
            public void run(){
                JFrame frame = new ListFrame("Note Access");
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        };
    }
}
