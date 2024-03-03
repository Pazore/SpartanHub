package me.spadelic.spspawn.help;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements TabExecutor {

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
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cError: /spartanhub help"));
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------&r &b&lSpartanHub Help &7&m------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &f/spartanhub help &7| &bSends this message"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &f/spartanhub reload &7| &bReloads the configuration"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &f/setspawn &7| &bSets the spawn location!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &f/spartanhub alias &7| &bSends command aliases!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &fMevune"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();


        completions.add("help");

        return completions;
    }
}
