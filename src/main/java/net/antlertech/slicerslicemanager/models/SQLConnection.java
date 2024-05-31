package net.antlertech.slicerslicemanager.models;

import java.sql.Connection;

public class SQLConnection {
    private static Connection connection;
    public static Connection getConnection() {
        return connection;
    }
    public static void setConnection(Connection Connection) {
        connection = Connection;
    }
}
