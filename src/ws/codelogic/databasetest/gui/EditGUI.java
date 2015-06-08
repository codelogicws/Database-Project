package ws.codelogic.databasetest.gui;

import ws.codelogic.databasetest.gui.controller.CreateNew;

import javax.swing.*;

public class EditGUI {

    public EditFrame frame;

    public void makeWindow(String title, String note, CreateNew.Handler handler){

        frame = new EditFrame("Note Access", title, note, handler);
        SwingUtilities.invokeLater(listWindow());

    }

    public void makeWindow(CreateNew.Handler handler) {
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
