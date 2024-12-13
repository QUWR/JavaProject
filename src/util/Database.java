package util;

import java.sql.*;

public class Database {
    Connection con = null;
    String url = "jdbc:mysql://localhost/data1?serverTimezone=Asia/Seoul";
    String user = "root";
    String password = "0625";

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
