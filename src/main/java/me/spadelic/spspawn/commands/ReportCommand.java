package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportCommand implements CommandExecutor, TabCompleter {

    private final SpartanHub plugin;
    //private final DatabaseManager databaseManager;

    public ReportCommand(SpartanHub plugin/*DatabaseManager databaseManager*/) {
        this.plugin = plugin;
        //this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player reporter = (Player) sender;

        if (!reporter.hasPermission("spartanhub.report")) {
            reporter.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have permission to use this command!"));
            return true;
        }

        if (args.length < 2) {
            reporter.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Usage: /report (player) (reason)"));
            return true;
        }

        Player reported = Bukkit.getPlayer(args[0]);

        if (reported == null || !reported.isOnline()) {
            reporter.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cPlayer not found or not online!"));
            return true;
        }

        String reason = buildReason(args);

        handleReport(reporter, reported, reason);

        //saveReportToDatabase(reporter, reported, reason);

        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("spartanhub.admin")) {
                staff.sendMessage(prefix + " " + reporter.getName() + " &7has reported " + reported.getName() + " for " + reason);
            }
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

    private String buildReason(String[] args) {
        StringBuilder reasonBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reasonBuilder.append(args[i]).append(" ");
        }
        return reasonBuilder.toString().trim();
    }

    private void handleReport(Player reporter, Player reported, String reason) {
        reporter.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have reported " + reported.getName() + " for " + reason));
    }
}

//    private void saveReportToDatabase(Player reporter, Player reported, String reason) {
//        Connection connection = databaseManager.getConnection();
//        if (connection != null) {
//            try {
//                PreparedStatement statement = connection.prepareStatement("INSERT INTO reports (reporter, reported, reason) VALUES (?, ?, ?)");
//                statement.setString(1, reporter.getUniqueId().toString());
//                statement.setString(2, reported.getUniqueId().toString());
//                statement.setString(3, reason);
//                statement.executeUpdate();
//                statement.close();
//
//                String reportMessage = plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("staff-prefix")) + reporter.getDisplayName() + " has reported " + reported.getDisplayName() + " for " + reason);
//                for (Player player : Bukkit.getOnlinePlayers()) {
//                    if (player.hasPermission("spartanhub.admin")) {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', reportMessage));
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
