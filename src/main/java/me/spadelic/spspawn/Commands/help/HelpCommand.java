package me.spadelic.spspawn.Commands.help;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    private final SpartanHub plugin;

    public HelpCommand(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player p = (Player) sender;

        String prefix = plugin.getConfig().getString("prefix");

        if (!p.hasPermission("spartanhub.help")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou don't have permission to use this command."));
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cUsage: /spartanhub help"));
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------&r &a&lAvSpawn Help &7&m------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/spartanhub help &7| &aSends this message"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/spartanhub reload &7| &aReloads the configuration"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/setspawn &7| &aSets the spawn location!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/spartanhub alias &7| &aSends command aliases!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &fMevune"));
        }

        return true;
    }
}
