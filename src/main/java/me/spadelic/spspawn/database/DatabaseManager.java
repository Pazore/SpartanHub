package me.spadelic.spspawn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseManager {

    private final JavaPlugin plugin;
    private Connection connection;

    public DatabaseManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void connect() {
        FileConfiguration config = plugin.getConfig();
        String host = config.getString("database.host");
        int port = config.getInt("database.port");
        String database = config.getString("database.name");
        String username = config.getString("database.username");
        String password = config.getString("database.password");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            connection = DriverManager.getConnection(url, username, password);
            getLogger().info("Connected to the database!");
        } catch (SQLException e) {
            getLogger().severe("Failed to connect to the database: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                getLogger().info("Disconnected from the database!");
            } catch (SQLException e) {
                getLogger().severe("Failed to disconnect from the database: " + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void insertStaffChatMessage(String playerName, String message) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO staff_chat (player_name, message) VALUES (?, ?)");
            statement.setString(1, playerName);
            statement.setString(2, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            getLogger().severe("Failed to insert staff chat message into the database: " + e.getMessage());
        }
    }

    private Logger getLogger() {
        return plugin.getLogger();
    }
}