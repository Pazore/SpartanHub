package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigReload implements TabExecutor {

    static SpartanHub plugin;

    public ConfigReload(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!p.hasPermission("spartanhub.reload")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.RED + "You are not permitted to this command!"));
                return true;
            } else {

                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.RED + "Usage: /spartanhub reload."));
                } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.GREEN + "SpartanHub reloaded!"));
                    plugin.reloadConfig();

                }

                }
            }
            return true;
        }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();


        completions.add("reload");

        return completions;
    }
}

