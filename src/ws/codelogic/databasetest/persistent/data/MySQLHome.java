package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;

import java.sql.*;
import java.util.Scanner;

public class MySQLHome implements PersistentData{

    private Connection connection;
    private Statement statement = null;
    private CallableStatement addNote = null;
    private ResultSet rs = null;
    private static final String TITLETABLE = "title";
    private static final String NOTETABLE = "note";

    public static MySQLHome createHomeSQLDatabase(){
        String password = getPassword();
        return new MySQLHome(password);
    }

    private MySQLHome(String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    "192.168.0.55" +
                    "/db_app?" +
                    "user=app&" +
                    "password=" +
                    password);
            createCallableStatements();
            statement = connection.createStatement();

            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        rs = null;

        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        statement = null;
    }

    private void createCallableStatements() {
        try {
            addNote = connection.prepareCall("{call addNote(?, ?)}");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Note note) {
        try {
            addNote.setString(1, note.getTitle());
            addNote.setString(2, note.getContent());
            addNote.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getTitles() {
        return new String[0];
    }

    @Override
    public Note getNote(int i) {
        return null;
    }

    public static String getPassword(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Password");
        return reader.next();
    }
}
