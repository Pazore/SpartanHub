package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    static SpartanHub plugin;

    public SetSpawn(SpartanHub plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = plugin.getConfig().getString("prefix");

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.hasPermission("spartanhub.setspawn")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission")));
            } else {

                Location location = p.getLocation();

                if (location.getWorld() != null) {

                    plugin.getConfig().set("spawn", location);
                    plugin.saveConfig();

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("spawn-location-message")));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("invalid-world")));
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
    }
        return true;
    }
}