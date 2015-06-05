package ws.codelogic.databasetest.persistent.data;

public class MockNonPersistentData implements PersistentData{

    String[] titles;
    String[] notes;
    int pointer = 0;

    public static MockNonPersistentData createWithCapacity(int capacity){
        return new MockNonPersistentData(capacity);
    }

    private MockNonPersistentData(int size){
        titles = new String[size];
        notes = new String[size];
    }

    @Override
    public void insert(Note note) {
        notes[pointer] = note.getContent();
        titles[pointer] = note.getTitle();
        pointer++;
    }

    @Override
    public String[] getTitles() {
            return titles;
    }

    @Override
    public Note getNote(int i) {
        return new Note(titles[i], notes[i]);
    }


}
