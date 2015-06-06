package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;

public interface PersistentData {

    void insert(Note note);
    String[] getTitles();
    Note getNote(int i);

    class OverCapacity extends RuntimeException {
    }
}
