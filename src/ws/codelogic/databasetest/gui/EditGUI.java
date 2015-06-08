package ws.codelogic.databasetest.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditGUI {

    public EditFrame frame;

    public void makeWindow(String title, String note, ActionListener handler){

        frame = new EditFrame(title, note, handler);
        SwingUtilities.invokeLater(listWindow());

    }

    public void makeWindow(ActionListener handler) {
        makeWindow("", "", handler);
    }

    private Runnable listWindow() {
        return new Runnable(){

            public void run(){
                frame.setSize(300, 500);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        };
    }

}
