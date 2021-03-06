package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;
import ws.codelogic.databasetest.gui.popup.PasswordRetriever;

import java.sql.*;
import java.util.Scanner;

public class MySQLHome implements PersistentData{

    private static MySQLHome mySQLHome = null;
    private Connection connection;
    private CallableStatement addNote;
    private CallableStatement removeNote;
    private int[] lastKnownIds;

    public static MySQLHome mySQLHomeSingleton(){
        if(mySQLHome == null){
            mySQLHome = new MySQLHome(getPassword());
        }
        return mySQLHome;
    }

    private MySQLHome(String password){
        try{
            setUpConnection(password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void setUpConnection(String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" +
                "z3r0.info" +
                "/db_app?" +
                "user=app&" +
                "password=" +
                password +
                "&noAccessToProcedureBodies=true");
        createCallableStatements();
    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Statement statementToClose, ResultSet resultToClose){
        if(resultToClose != null){
            try{
                resultToClose.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statementToClose != null){
            try{
                statementToClose.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createCallableStatements() {
        try {
            createCallable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCallable() throws SQLException {
        addNote = connection.prepareCall("{call addNote(?, ?)}");
        removeNote = connection.prepareCall("{call removeNote(?)}");
    }

    @Override
    public void insert(Note note) {
        try {
            doInsert(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editNote(int id, Note note) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE title SET title = '" +
                    note.getTitle() + "' where id = " + lastKnownIds[id]);
            Statement statement2 = connection.createStatement();
            statement.executeUpdate("UPDATE note SET note = '" +
                    note.getContent() + "' where id = " + lastKnownIds[id]);
            statement.close();
            statement2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doInsert(Note note) throws SQLException {
        addNote.setString(1, note.getTitle());
        addNote.setString(2, note.getContent());
        addNote.executeQuery();
    }

    @Override
    public void removeNote(int index){
        try {
            doRemoveNote(lastKnownIds[index]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doRemoveNote(int lastKnownId) throws SQLException {
        removeNote.setInt(1, lastKnownId);
        removeNote.executeQuery();
    }

    @Override
    public String[] getTitles() {
        String[] titles = null;
        try {
            titles = doGetTitle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
    }

    private String[] doGetTitle() throws SQLException {
        String[] titles = null;
        Statement newStatement = connection.createStatement();
        ResultSet newResults = newStatement.executeQuery("SELECT id, title FROM title");
        int tableCount = getTableCount();
        titles = new String[tableCount];
        lastKnownIds = new int[tableCount];
        int index = 0;
        while(newResults.next()){
            titles[index] = newResults.getString("title");
            lastKnownIds[index] = newResults.getInt("id");
            index++;
        }
        close(newStatement, newResults);
        return titles;
    }

    private int getTableCount(){
        int count = 0;
        try {
            count = doTableCount(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private int doTableCount(int count) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS total FROM title");
        while(rs.next()){
            count = rs.getInt("total");
        }
        return count;
    }

    @Override
    public Note getNote(int index) {
        int id = lastKnownIds[index];
        return new Note(getTitle(id), getNoteContent(id));
    }

    private String getTitle(int id) {
        return getElement(id, "title");
    }

    private String getNoteContent(int id){
        return getElement(id, "note");
    }

    private String getElement(int id, String columnAndTable) {
        String element = null;
        try {
            element = doGetElement(id, columnAndTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return element;
    }

    private String doGetElement(int id, String columnAndTable) throws SQLException {
        String element = null;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT " + columnAndTable + " FROM " + columnAndTable + " WHERE id = " + id);
        if(rs.next()){
            element = rs.getString(columnAndTable);
        }
        return element;
    }

    public static String getPassword(){
        PasswordRetriever retriever = new PasswordRetriever();
        return retriever.getPassword();
    }
}
