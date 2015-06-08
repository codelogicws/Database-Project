package ws.codelogic.databasetest.gui;

import javax.swing.*;

public class ListGUI {

    private static ListGUI listGUI;
    private ListFrame frame;

    public static ListGUI listSingleton(){
        if(listGUI == null)
            listGUI = new ListGUI();
        return listGUI;
    }

    private ListGUI(){
    }

    public void makeWindow(){

        SwingUtilities.invokeLater(listWindow());

    }

    private Runnable listWindow() {
        return new Runnable(){
            public void run(){
                frame = new ListFrame();
                frame.setSize(300, 500);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        };
    }

    public void update() {
        frame.refresh();
        frame.revalidate();
        frame.repaint();
    }
}
