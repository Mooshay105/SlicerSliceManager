package net.antlertech.slicerslicemanager.models;

public class SQLData {
    private static final String host = "localhost";
    private static final int port = 3306;
    private static final String database = "SlicesDatabase";
    private static final String username = "SlicerSlices";
    private static final String password = "SlicerSlices01!";

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
