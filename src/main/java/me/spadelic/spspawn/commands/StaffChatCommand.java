package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class StaffChatCommand implements CommandExecutor {

    private final SpartanHub plugin;
    private final Set<UUID> staffChatEnabledPlayers;
    private final me.spadelic.spspawn.DataBase.DatabaseManager databaseManager;

    public StaffChatCommand(SpartanHub plugin, Set<UUID> staffChatEnabledPlayers, me.spadelic.spspawn.DataBase.DatabaseManager databaseManager) {
        this.plugin = plugin;
        this.staffChatEnabledPlayers = staffChatEnabledPlayers;
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("spartanhub.staffchat")) {
            player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        }

        if (args.length == 0) {
            if (staffChatEnabledPlayers.contains(player.getUniqueId())) {
                staffChatEnabledPlayers.remove(player.getUniqueId());
                player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("left-staffchat")));
            } else {
                staffChatEnabledPlayers.add(player.getUniqueId());
                player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("entered-staffchat")));
            }
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        String formattedMessage = CC.translate(plugin.getConfig().getString("staff-prefix")) + player.getName() + "&f: " + message.toString();
        for (Player staff : plugin.getServer().getOnlinePlayers()) {
            if (staff.hasPermission("spartanhub.staffchat")) {
                staff.sendMessage(formattedMessage);
            }
        }

        databaseManager.insertStaffChatMessage(player.getName(), message.toString());

        return true;
    }
}
