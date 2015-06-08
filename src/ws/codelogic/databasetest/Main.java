package ws.codelogic.databasetest;

import ws.codelogic.databasetest.gui.ListGUI;

public class Main {

    public static void main(String[] args){
        ListGUI gui = ListGUI.listSingleton();
        gui.makeWindow();
    }

}