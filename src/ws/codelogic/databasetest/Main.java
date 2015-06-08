package ws.codelogic.databasetest;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        PersistentData persistentData = MySQLHome.createHomeSQLDatabase();
        Note myNote = new Note("Java", "new note");
        persistentData.insert(myNote);
        String[] titles = persistentData.getTitles();
        Note note = persistentData.getNote(3);
        persistentData.close();
    }

}