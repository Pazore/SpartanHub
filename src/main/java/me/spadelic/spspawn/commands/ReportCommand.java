package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;

public class ReportCommand implements CommandExecutor, TabCompleter {

    public ReportCommand() {
        // Constructor, if needed
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

        String reason = buildReason(args);

        handleReport(reporter, reported, reason);

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
        reporter.sendMessage(CC.translate("&aYou have submitted a report!"));
    }
}