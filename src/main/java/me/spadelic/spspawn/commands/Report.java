package me.spadelic.spspawn.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.spadelic.spspawn.DataBase.DatabaseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Bukkit;

public class Report implements CommandExecutor, TabCompleter {

    private final DatabaseManager databaseManager;

    public Report(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player reporter = (Player) sender;

        if (!reporter.hasPermission("spartanhub.report")) {
            reporter.sendMessage("You don't have permission to use this command!");
            return true;
        }

        if (args.length < 2) {
            reporter.sendMessage("Usage: /report <player> <reason>");
            return true;
        }

        Player reported = Bukkit.getPlayer(args[0]);

        if (reported == null || !reported.isOnline()) {
            reporter.sendMessage("Player not found or not online.");
            return true;
        }

        StringBuilder reasonBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reasonBuilder.append(args[i]).append(" ");
        }
        String reason = reasonBuilder.toString().trim();

        Connection connection = databaseManager.getConnection();
        if (connection != null) {
            try {
                String sql = "INSERT INTO reports (reporter, reported, reason) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, reporter.getUniqueId().toString());
                statement.setString(2, reported.getUniqueId().toString());
                statement.setString(3, reason);
                statement.executeUpdate();
                statement.close();
                reporter.sendMessage("Your report has been submitted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                reporter.sendMessage("An error occurred while submitting your report.");
            }
        } else {
            reporter.sendMessage("Failed to connect to the database.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            completions.add(player.getName());
        }
        return completions;
    }
}
