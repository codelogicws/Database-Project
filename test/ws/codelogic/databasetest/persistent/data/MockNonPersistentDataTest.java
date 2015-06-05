package ws.codelogic.databasetest.persistent.data;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MockNonPersistentDataTest {

    private MockNonPersistentData data;
    public static final int CAPACITY = 5;

    @Before
    public void setUp(){
        data = MockNonPersistentData.createWithCapacity(CAPACITY);
    }

    @Test
    public void insertAnObject_ObjectShouldAppearOnNoteList() {
        String title = "test title";
        insert(title, "");
        String[] list = data.getTitles();
        assertEquals(list[0], title);
    }

    @Test
    public void insertANote_GetFullNoteBack() {
        String title = "test2";
        String noteText = "my note";
        insert(title, noteText);
        Note note = data.getNote(0);
        assertEquals(note.getTitle(), title);
        assertEquals(note.getContent(), noteText);
    }

    @Test
    public void insertMultiNotes_GetAllNotesBack() {
        Note[] notes = createPopulatedNotes();
        insertAll(notes);
        assertEquivalent(notes, data);
    }

    @Test
    public void createNotes_OpenASpecificNote() {
        Note[] notes = createPopulatedNotes();
        insertAll(notes);
        assertEquals("This is a note for title1.", data.getNote(1).getContent());
    }

    private void insertAll(Note[] notes) {
        for(Note n : notes){
            insert(n.getTitle(), n.getContent());
        }
    }

    private void assertEquivalent(Note[] notes, MockNonPersistentData data) {
        for(int i=0;i<notes.length;i++){
            assertEquals(notes[i].getTitle(), data.getNote(i).getTitle());
            assertEquals(notes[i].getTitle(), data.getNote(i).getTitle());
        }
    }

    private Note[] createPopulatedNotes() {
        int length = 4;
        return createNotes(length);
    }

    private Note[] createNotes(int length) {
        Note[] notes = new Note[length];
        for(int i=0;i<length;i++){
            notes[i] = new Note("Title" + i, "This is a note for title" + i + ".");
        }
        return notes;
    }

    private void insert(HashMap<String, String> notes){
        for(String s : notes.keySet()){
            insert(s, notes.get(s));
        }
    }

    private void insert(String title, String noteText) {
        data.insert(new Note(title, noteText));
    }


}