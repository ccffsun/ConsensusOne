package db;

import java.sql.*;

public class Database {
    static Connection connection;

    private Database(){}

    public static Connection getConnection() throws SQLException {
        if(connection==null){
            String url = "jdbc:mysql://localhost:3306/ConsensusOne?serverTimezone=UTC";
            String user = "root";
            String password = "ccffsun86";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
