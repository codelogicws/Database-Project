package ws.codelogic.databasetest.persistent.data;

public interface PersistentData {

    void insert(Note note);
    String[] getTitles();
    Note getNote(int i);

}
