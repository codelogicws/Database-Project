package ws.codelogic.databasetest;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

public class Main {
    public static void main(String[] args){
        PersistentData persistentData = MySQLHome.createHomeSQLDatabase();
        Note myNote = new Note("Java Note", "This is my note done from inside the java language");
        persistentData.insert(myNote);
    }
}