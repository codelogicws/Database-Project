package ws.codelogic.databasetest.persistent.data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MockNonPersistentDataTest {

    private MockNonPersistentData data;

    @Before
    public void setUp(){
        data = MockNonPersistentData.createWithCapacity5();
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

    private void insert(String title, String noteText) {
        data.insert(new Note(title, noteText));
    }


}