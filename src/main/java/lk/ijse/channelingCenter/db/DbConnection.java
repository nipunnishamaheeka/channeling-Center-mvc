package lk.ijse.channelingCenter.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  DbConnection {
    private static DbConnection dbconnection;
    @Getter
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/channelingcenter",
                "root",
                "Mahee@10985"

        );
    }

    public static DbConnection getInstance() throws SQLException {
        if (dbconnection == null) {
            dbconnection = new DbConnection();
            return dbconnection;
        } else {
            return dbconnection;
        }
    }

//    public Connection getConnection(){
//        return connection;
//    }
}
