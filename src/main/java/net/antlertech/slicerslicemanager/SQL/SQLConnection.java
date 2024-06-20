package net.antlertech.slicerslicemanager.SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement() {
        return statement;
    }
    public static void setStatement(Connection Connection) throws SQLException {
        statement = Connection.createStatement();
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void setConnection(Connection Connection) {
        connection = Connection;
    }
}
