//package me.spadelic.spspawn.Commands.Misc;
//
//import com.avaje.ebean.PagingList;
//import me.spadelic.spspawn.SpartanHub;
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//
//public class CommandBlocker implements CommandExecutor {
//
//    static SpartanHub plugin;
//
//    public CommandBlocker(SpartanHub plugin) {
//        this.plugin = plugin;
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        String prefix = plugin.getConfig().getString("prefix");
//
//        if (!(sender instanceof Player)) {
//            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
//            return true;
//        }
//
//        Player p = (Player) sender;
//
//        if (!p.hasPermission("spartanhub.bypass")) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("no-permission")));
//            return false;
//        } else {
//            if (p.performCommand("pl")) {
//                p.sendMessage("");
//            }
//        }
//
//        return true;
//    }
//}
