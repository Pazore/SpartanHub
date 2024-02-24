package me.spadelic.spspawn.Commands.help;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AliasCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            if (!p.hasPermission("spartanhub.viewalias")){
                p.sendMessage(ChatColor.RED + "You are not permitted to execute this command!");
                return true;
            }

            if (args.length == 0){

            } else if (args.length == 1 && args[0].equalsIgnoreCase("alias")){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------&r &a&lSpartanHub Alias &7&m------"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
//p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/setlobby "));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/spartanhub &7| &f/shub"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &fMevune"));
            }

        }

        return true;
    }
}
