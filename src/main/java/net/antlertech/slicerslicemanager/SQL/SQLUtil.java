package net.antlertech.slicerslicemanager.SQL;

import net.antlertech.slicerslicemanager.models.messages;
import java.sql.*;
import java.util.UUID;

import static net.antlertech.slicerslicemanager.SQL.SQLConnection.*;
import static org.bukkit.Bukkit.getLogger;

public class SQLUtil {
    public static int setPlayerIDByPlayerIDandXpos(UUID playerID, int xpos) throws SQLException {
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
        return 0;
    }

    public static String getPlayerIDByXpos(String xpos) throws SQLException {
        String query = "SELECT * FROM slices WHERE xpos = '" + xpos + "'";
        ResultSet resultSet = getStatement().executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getString("playerID");
        }
        return null;
    }

    public static void addPlayerIDToDatabase(UUID playerID) throws SQLException {
        String addPlayerIDCommand = "INSERT IGNORE INTO isPlayerAllowedToClaim VALUES ('" + playerID.toString() + "', '1');";
        getStatement().execute(addPlayerIDCommand);
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

    public static void setUpSQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + SQLData.getHost() + ":" + SQLData.getPort() + "/" + SQLData.getDatabase() + "?useSSL=false", SQLData.getUsername(), SQLData.getPassword());
        setConnection(connection);
        setStatement(connection);
        setupTables();
    }

    private static void setupTables() throws SQLException {
        getStatement().execute("CREATE TABLE IF NOT EXISTS slices (xpos VARCHAR(5) primary key, playerID VARCHAR(36))");
        getStatement().execute("CREATE TABLE IF NOT EXISTS isPlayerAllowedToClaim (playerID VARCHAR(36) primary key, isAllowedToClaim BOOLEAN)");
    }

    public static void closeSQLConnection() throws SQLException {
        getConnection().close();
        getStatement().close();
    }
}
