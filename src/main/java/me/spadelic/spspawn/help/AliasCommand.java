package me.spadelic.spspawn.help;

import me.spadelic.spspawn.utils.CC;
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
                p.sendMessage(CC.translate("&7&m------&r &a&lSpartanHub Alias &7&m------"));
                p.sendMessage(CC.translate( ""));
//p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a- &f/setlobby "));
                p.sendMessage(CC.translate("&a- &f/spartanhub &7| &f/shub"));
                p.sendMessage(CC.translate(""));
                p.sendMessage(CC.translate("&a- &fMevune"));
            }

        }

        return true;
    }
}
