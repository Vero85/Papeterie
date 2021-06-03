package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private final static String url = "jdbc:sqlite:papeterie_db.sqlite";

    public static Connection recupConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

}
