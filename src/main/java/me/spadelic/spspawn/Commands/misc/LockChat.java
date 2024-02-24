package me.spadelic.spspawn.Commands.misc;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockChat implements CommandExecutor {

    private static SpartanHub plugin;
    private static boolean chatLocked = false;

    public LockChat(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.lockchat")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("no-permission")));
            return true;
        } else {
            String playerPAPI = p.getDisplayName();
            if (chatLocked) {
                plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("chat-unlocked").replace("%player%", playerPAPI)));
            } else {
                plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("chat-locked").replace("%player%", playerPAPI)));
            }
            chatLocked = !chatLocked;
        }

        return true;
    }
}