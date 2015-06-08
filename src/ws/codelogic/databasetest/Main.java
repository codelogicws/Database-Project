package ws.codelogic.databasetest;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.ListGUI;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
//        PersistentData persistentData = MySQLHome.createHomeSQLDatabase();
//        persistentData.getTitles();
//        Note note = new Note("CRAZY", "this is a note edit for the first time");
//        persistentData.editNote(0, note);
//        Note myNote = new Note("more notes of course", "my new entry note");
//        persistentData.insert(myNote);
//        String[] titles = persistentData.getTitles();
//        Note note = persistentData.getNote(3);
//        persistentData.close();
        ListGUI gui = ListGUI.listSingleton();
        gui.makeWindow();
    }

}