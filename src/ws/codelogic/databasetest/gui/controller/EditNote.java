package ws.codelogic.databasetest.gui.controller;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.EditGUI;
import ws.codelogic.databasetest.gui.ListGUI;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNote {
    private PersistentData pd;
    private EditGUI editFrame;
    private Note note;
    private int noteIndex;

    public EditNote(int noteIndex, Note note){
        this.note = note;
        this.noteIndex = noteIndex;
    }

    public void createNewWindow(){
        pd = MySQLHome.mySQLHomeSingleton();
        editFrame = new EditGUI();
        editFrame.makeWindow(note.getTitle(), note.getContent(), new EditHandler());
    }

    public class EditHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Debug-EditHandler: edit note attempt");
            Note note = new Note(editFrame.frame.getNoteTitle(), editFrame.frame.getNote());
            pd.editNote(noteIndex, note);
            ListGUI.listSingleton().update();
            close();
        }

        private void close() {
            editFrame.frame.setVisible(false);
            editFrame.frame.dispose();
        }
    }
}
