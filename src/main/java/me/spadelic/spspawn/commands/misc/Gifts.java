/*package me.spadelic.spspawn.Commands.Misc;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Gifts implements TabExecutor {

    static SpartanHub plugin;

    public Gifts(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        Player target = Bukkit.getPlayer(args[0]);

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("avspawn.sendgift")){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("no-permission")));
            return true;
        } else {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Error: /avspawn gift (player)");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("gift")){

            } else if (args.length == 2) {
                List<String> completions = new ArrayList<>();
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    completions.add(onlinePlayer.getName());
                }
                StringUtil.copyPartialMatches(args[0], completions, completions);
                return true;
            } else {
                if (target == null || !p.isOnline()) {
                    p.sendMessage(ChatColor.RED + "Error: Player is not online");
                    return true;
                }

                target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aYou have been gifted by " + p.getName()));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aYou have gifted " + target.getName()));

            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            completions.add(p.getName());
        }

        completions.add("gift");

        return completions;
    }
}*/
