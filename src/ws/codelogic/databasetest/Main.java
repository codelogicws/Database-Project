package ws.codelogic.databasetest;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.ListGUI;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        ListGUI gui = ListGUI.listSingleton();
        gui.makeWindow();
    }

}