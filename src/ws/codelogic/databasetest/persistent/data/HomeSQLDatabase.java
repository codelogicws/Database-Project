package ws.codelogic.databasetest.persistent.data;

import ws.codelogic.databasetest.data.Note;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class HomeSQLDatabase implements PersistentData{

    private Connection connection;
    private static final String TITLETABLE = "title";
    private static final String NOTETABLE = "note";

    public static HomeSQLDatabase createHomeSQLDatabase(){
        String password = getPassword();
        return new HomeSQLDatabase("68.4.153.128", "SQLEXPRESS", "codelogic.ws", "sa", "1433", password);
    }

    private HomeSQLDatabase(String ip, String view, String database, String user, String port, String password){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + ip + "\\" + view + ":" + port +  ";" +
                    "database="+ database + ";"+
                    "user="+user+";" +
                    "password=" + password + ";";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Note note) {
//        HashMap<String, String> fields = new HashMap<>();
//        fields.put("note", "")
//        insertToTable(NOTETABLE, )
        doStatement("insert into note (id, note) values (2, 'test note from java')");
    }

//    private void insertToTable(String table, HashMap<String, String> elements){
//        doStatement("INSERT INTO " +
//                table +
//                " (" +
//                fields(elements.keySet()) +
//                ") VALUES (" +
//                values(elements.values()) +
//                ");"
//        );
//    }

    private void doStatement(String command) {
        System.out.println("Debug-HomeSQLDatabase: attempted command " + command);
        try {
            Statement statement = connection.createStatement();
            statement.execute(command);
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
