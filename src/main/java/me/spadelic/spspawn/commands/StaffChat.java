package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    private final SpartanHub plugin;

    public StaffChat(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("spartanhub.staffchat")) {
            player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        } else {

            if (args.length == 0) {
                if (plugin.getStaffChatEnabledPlayers().contains(player.getUniqueId())) {
                    plugin.getStaffChatEnabledPlayers().remove(player.getUniqueId());
                    player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("left-staffchat")));
                } else {
                    plugin.getStaffChatEnabledPlayers().add(player.getUniqueId());
                    player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("entered-staffchat")));
                }
                return true;
            }

            if (plugin.getStaffChatEnabledPlayers().contains(player.getUniqueId())) {
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg).append(" ");
                }
                plugin.sendStaffChatMessage(player, message.toString());
            } else {
                plugin.getStaffChatEnabledPlayers().add(player.getUniqueId());
                plugin.sendStaffChatMessage(player, String.join(" ", args));
            }

            return true;
        }
    }
}
