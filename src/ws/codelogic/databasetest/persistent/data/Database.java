package ws.codelogic.databasetest.persistent.data;
import java.sql.*;
import java.util.Scanner;

public class Database {

    public void connect(){

        String password = getPassword();

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionUrl = "jdbc:sqlserver://68.4.153.128:1433\\SQLEXPRESS;" +
                    "database=codelogic.ws;" +
                    "user=sa;" +
                    "password=" + password;
            Connection con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected.");

            String SQL = "SELECT * FROM test";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e1) {
            System.out.println("error");
            e1.printStackTrace();
        } catch(Exception e){
            System.out.println("error");
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public String getPassword(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Password");
        return reader.next();
    }
}