package net.antlertech.slicerslicemanager.SQL;

import net.antlertech.slicerslicemanager.models.SQLData;
import net.antlertech.slicerslicemanager.models.messages;
import java.sql.*;
import java.util.UUID;
import net.antlertech.slicerslicemanager.models.SQLConnection;
import static org.bukkit.Bukkit.getLogger;

public class SQLUtil {
    public static int setPlayerIDByPlayerIDandXpos(UUID playerID, int xpos) {
        try {
            String checkAllowedToClaimQuery = "SELECT * FROM isPlayerAllowedToClaim WHERE playerID = '" + playerID.toString() + "';";
            String addSliceCommand = "INSERT INTO slices VALUES ('" + xpos + "', '" + playerID.toString() + "');";
            String updateAllowedToClaimCommand = "UPDATE isPlayerAllowedToClaim SET isAllowedToClaim = '0' WHERE playerID = '" + playerID.toString() + "';";
            boolean isAllowedToClaim = false;
            Statement statement = SQLConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(checkAllowedToClaimQuery);
            if (resultSet.next()) {
                isAllowedToClaim = resultSet.getBoolean("isAllowedToClaim");
            }
            if (isAllowedToClaim) {
                statement.execute(addSliceCommand);
                statement.execute(updateAllowedToClaimCommand);
            } else {
                return 1;
            }
            statement.close();
        } catch (SQLException e) {
            return 3;
        }
        return 0;
    }

    public static String getPlayerIDByXpos(String xpos) {
        String query = "SELECT * FROM slices WHERE xpos = '" + xpos + "'";
        try {
            Statement statement = SQLConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("playerID");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPlayerIDToDatabase(UUID playerID) {
        try {
            String addPlayerIDCommand = "INSERT IGNORE INTO isPlayerAllowedToClaim VALUES ('" + playerID.toString() + "', '1');";
            Statement statement = SQLConnection.getConnection().createStatement();
            statement.execute(addPlayerIDCommand);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "addPlayerIDToDatabaseSQLException");
        }
    }

    public static void approveSlice(UUID playerID) {
        try {
            String approveSliceCommand = "UPDATE isPlayerAllowedToClaim SET isAllowedToClaim = '1' WHERE playerID = '" + playerID.toString() + "';";
            Statement statement = SQLConnection.getConnection().createStatement();
            statement.execute(approveSliceCommand);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "approveSliceSQLException");
        }
    }


    public static void closeSQLConnection() {
        try {
            SQLConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "saveSlicesSQLException");
        }
    }
    public static void setUpSQL() {
        String host = SQLData.getHost();
        int port = SQLData.getPort();
        String database = SQLData.getDatabase();
        String username = SQLData.getUsername();
        String password = SQLData.getPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
            SQLConnection.setConnection(connection);
            setupTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "connectClassNotFoundException");
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "connectSQLException");
        }
    }
    private static void setupTables() {
        try {
            Statement statement = SQLConnection.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS slices (xpos VARCHAR(5) primary key, playerID VARCHAR(36))");
            statement.execute("CREATE TABLE IF NOT EXISTS isPlayerAllowedToClaim (playerID VARCHAR(36) primary key, isAllowedToClaim BOOLEAN)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            getLogger().info(messages.getSQLErrorMessage() + "setupTableSQLException");
        }
    }
}
