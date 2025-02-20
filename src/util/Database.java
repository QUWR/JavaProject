package util;

import java.sql.*;

public class Database {
    Connection con = null;
    String url = "";
    String user = "";
    String password = "";

    public Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }




}
