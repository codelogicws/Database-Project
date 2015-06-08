package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;

public interface PersistentData {

    void insert(Note note);

    void removeNote(int id);

    String[] getTitles();
    Note getNote(int i);
    void close();

    class OverCapacity extends RuntimeException {
    }
}
