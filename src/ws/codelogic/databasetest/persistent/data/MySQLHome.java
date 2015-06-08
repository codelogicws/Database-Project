package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;

import java.sql.*;
import java.util.Scanner;

public class MySQLHome implements PersistentData{

    private Connection connection;
    private CallableStatement addNote;
    private CallableStatement removeNote;
    private int[] lastKnowenIds;
    private static final String TITLETABLE = "title";
    private static final String NOTETABLE = "note";

    public static MySQLHome createHomeSQLDatabase(){
//        String password = getPassword();
        //temp password
        return new MySQLHome("0p3nAcc355");
    }

    private MySQLHome(String password){
        try{
            setUpConnection(password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        System.out.println("Connected");
    }

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
            createCallables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCallables() throws SQLException {
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

    private void doInsert(Note note) throws SQLException {
        addNote.setString(1, note.getTitle());
        addNote.setString(2, note.getContent());
        addNote.executeQuery();
    }

    @Override
    public void removeNote(int index){
        try {
            doRemoveNote(lastKnowenIds[index]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doRemoveNote(int lastKnowenId) throws SQLException {
        removeNote.setInt(1, lastKnowenId);
        removeNote.executeQuery();
    }

    @Override
    public String[] getTitles() {
        String[] titles = null;
        try {
            titles = doGetTitle(titles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
    }

    private String[] doGetTitle(String[] titles) throws SQLException {
        Statement newStatement = connection.createStatement();
        ResultSet newResults = newStatement.executeQuery("SELECT id, title FROM title");
        int tableCount = tableCount();
        titles = new String[tableCount];
        lastKnowenIds = new int[tableCount];
        int index = 0;
        while(newResults.next()){
            titles[index] = newResults.getString("title");
            lastKnowenIds[index] = newResults.getInt("id");
            index++;
        }
        close(newStatement, newResults);
        return titles;
    }

    private int tableCount(){
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
        int id = lastKnowenIds[index];
        Note note = new Note(getTitle(id), getNoteContent(id));
        return note;
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + columnAndTable + " FROM " + columnAndTable + " WHERE id = " + id);
            while(rs.next()){
                element = rs.getString(columnAndTable);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static String getPassword(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Password");
        return reader.next();
    }
}
