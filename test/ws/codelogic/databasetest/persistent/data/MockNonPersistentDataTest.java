package ws.codelogic.databasetest.persistent.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MockNonPersistentDataTest {

    private MockNonPersistentData data;

    @Before
    public void setUp(){
        data = new MockNonPersistentData();
    }

    @Test
    public void insertData(){
        data.insert("My Title", "My content");
    }

    @Test
    public void insertedDataIsInList() {
        String title = "testTitle";
        data.insert(title, "empty");
        String[] titles = data.getTitles();
        assertEquals(title, titles[0]);
    }

    @Test
    public void multiInsert_titleListYieldsNewTitles(){
        String[] testTitles = {"title1", "title2", "title3"};
        insertMulti(testTitles);
        String[] resultingTitles = data.getTitles();
        assertEquals(testTitles[0], resultingTitles[0]);
        assertEquals(testTitles[1], resultingTitles[1]);
        assertEquals(testTitles[2], resultingTitles[2]);
    }

    private void insertMulti(String[] testTitles) {
        for(String s : testTitles)
            data.insert(s, "dummy");
    }

}