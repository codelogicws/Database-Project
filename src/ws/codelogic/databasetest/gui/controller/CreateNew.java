package ws.codelogic.databasetest.gui.controller;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.EditGUI;
import ws.codelogic.databasetest.gui.ListGUI;
import ws.codelogic.databasetest.persistent.data.MySQLHome;
import ws.codelogic.databasetest.persistent.data.PersistentData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNew {

    private PersistentData pd;
    private EditGUI editFrame;

    public void createNewWindow(){
        pd = MySQLHome.mySQLHomeSingleton();
        editFrame = new EditGUI();
        editFrame.makeWindow(new Handler());
    }

    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Note note = new Note(editFrame.frame.getNoteTitle(), editFrame.frame.getNote());
            pd.insert(note);
            ListGUI.listSingleton().update();
            close();
        }

        private void close() {
            editFrame.frame.setVisible(false);
            editFrame.frame.dispose();
        }
    }
}
