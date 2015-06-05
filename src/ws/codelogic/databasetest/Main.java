package ws.codelogic.databasetest;

import ws.codelogic.databasetest.persistent.data.Database;

public class Main {
    public static void main(String[] args){
        Database db = new Database();
        db.connect();
    }
}