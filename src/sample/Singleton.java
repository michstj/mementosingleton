package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {

    static Connection conn = null;

    private Singleton(){

    }
    public static Connection getConn(){
        try {
            if(conn == null){
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/memento" , "root", "");

            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return conn;
    }

}