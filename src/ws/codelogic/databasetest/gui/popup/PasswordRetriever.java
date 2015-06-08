package ws.codelogic.databasetest.gui.popup;

import javax.swing.*;

public class PasswordRetriever {

    public String getPassword() {
        String password = "";
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            password = new String(pf.getPassword());
        }
        return password;
    }
}
