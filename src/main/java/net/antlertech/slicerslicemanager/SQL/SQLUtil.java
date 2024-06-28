package net.antlertech.slicerslicemanager.SQL;

import net.antlertech.slicerslicemanager.models.messages;
import java.sql.*;
import java.util.UUID;

import static net.antlertech.slicerslicemanager.SQL.SQLConnection.*;
import static org.bukkit.Bukkit.getLogger;

public class SQLUtil {
    public static int setPlayerIDByPlayerIDandXpos(UUID playerID, int xpos) {
        try {
            String checkAllowedToClaimQuery = "SELECT * FROM isPlayerAllowedToClaim WHERE playerID = '" + playerID.toString() + "';";
            String addSliceCommand = "INSERT INTO slices VALUES ('" + xpos + "', '" + playerID.toString() + "');";
            String updateAllowedToClaimCommand = "UPDATE isPlayerAllowedToClaim SET isAllowedToClaim = '0' WHERE playerID = '" + playerID.toString() + "';";
            boolean isAllowedToClaim = false;
            ResultSet resultSet = getStatement().executeQuery(checkAllowedToClaimQuery);
            if (resultSet.next()) {
                isAllowedToClaim = resultSet.getBoolean("isAllowedToClaim");
            }
            if (isAllowedToClaim) {
                getStatement().execute(addSliceCommand);
                getStatement().execute(updateAllowedToClaimCommand);
            } else {
                return 1;
            }
        } catch (SQLException e) {
            return 3;
        }
        return 0;
    }

    public static String getPlayerIDByXpos(String xpos) {
        String query = "SELECT * FROM slices WHERE xpos = '" + xpos + "'";
        try {
            ResultSet resultSet = getStatement().executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("playerID");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addPlayerIDToDatabase(UUID playerID) {
        try {
            String addPlayerIDCommand = "INSERT IGNORE INTO isPlayerAllowedToClaim VALUES ('" + playerID.toString() + "', '1');";
            getStatement().execute(addPlayerIDCommand);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "addPlayerIDToDatabaseSQLException");
            return false;
        }
    }

    public static boolean approveSlice(UUID playerID) {
        try {
            String approveSliceCommand = "UPDATE isPlayerAllowedToClaim SET isAllowedToClaim = '1' WHERE playerID = '" + playerID.toString() + "';";
            getStatement().execute(approveSliceCommand);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "approveSliceSQLException");
            return false;
        }
    }

    public static boolean setUpSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + SQLData.getHost() + ":" + SQLData.getPort() + "/" + SQLData.getDatabase() + "?useSSL=false", SQLData.getUsername(), SQLData.getPassword());
            setConnection(connection);
            setStatement(connection);
            boolean setupTables = setupTables();
            if (!setupTables) {
                return false;
            }
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "connectClassNotFoundException");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "connectSQLException");
            return false;
        }
    }

    private static boolean setupTables() {
        try {
            getStatement().execute("CREATE TABLE IF NOT EXISTS slices (xpos VARCHAR(5) primary key, playerID VARCHAR(36))");
            getStatement().execute("CREATE TABLE IF NOT EXISTS isPlayerAllowedToClaim (playerID VARCHAR(36) primary key, isAllowedToClaim BOOLEAN)");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "setupTableSQLException");
            return false;
        }
    }
    public static boolean closeSQLConnection() {
        try {
            getConnection().close();
            getStatement().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "closeConnectionAndStatementSQLException");
            return false;
        }
    }
}
