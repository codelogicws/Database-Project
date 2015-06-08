package ws.codelogic.databasetest.gui;

import javax.swing.*;

public class EditGUI {

    public void makeWindow(String title, String note){

        SwingUtilities.invokeLater(listWindow(title, note));

    }

    public void makeWindow() {
        makeWindow("", "");
    }

    private Runnable listWindow(String title, String note) {
        return new Runnable(){
            public void run(){
                JFrame frame = new EditFrame("Note Access", title, note);
                frame.setSize(300, 500);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        };
    }

}
