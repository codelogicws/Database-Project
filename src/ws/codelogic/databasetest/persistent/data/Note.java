package ws.codelogic.databasetest.persistent.data;

public class Note {

    private String title;
    private String note;

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return note;
    }

    public String toString(){
        return title + " " + note;
    }
}
