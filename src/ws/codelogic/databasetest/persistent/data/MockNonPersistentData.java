package ws.codelogic.databasetest.persistent.data;

import java.util.ArrayList;

public class MockNonPersistentData implements PersistentData{

    String[] titles;
    int pointer = 0;

    public MockNonPersistentData(){
        titles = new String[5];
    }

    @Override
    public void insert(String title, String note) {
        titles[pointer++] = title;
    }

    @Override
    public String[] getTitles() {
            return titles;
    }
}
