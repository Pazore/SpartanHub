package me.spadelic.spspawn.help;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AliasCommand implements TabExecutor {
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
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------&r &b&lSpartanHub Alias &7&m------"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  ""));
//p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/setlobby "));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &f/spartanhub &7| &f/shub"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b- &fMevune"));
            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();


        completions.add("alias");

        return completions;
    }
}




