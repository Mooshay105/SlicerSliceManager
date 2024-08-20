package net.antlertech.slicerslicemanager.SQL;

import net.antlertech.slicerslicemanager.SlicerSliceManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLData {
    private static final SlicerSliceManager plugin = SlicerSliceManager.getPlugin();
    private static final String host = plugin.getConfig().getString("host");
    private static final int port = plugin.getConfig().getInt("port");
    private static final String database = plugin.getConfig().getString("database");
    private static final String username = plugin.getConfig().getString("username");
    private static final String password = plugin.getConfig().getString("password");
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
    public static String getHost() {
        return host;
    }
    public static int getPort() {
        return port;
    }
    public static String getDatabase() {
        return database;
    }
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }
}
