package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public class FreezeCommand implements TabExecutor, Listener {

    private final SpartanHub plugin = SpartanHub.getPlugin();
    private final Map<Player, Boolean> frozenPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        String staffPrefix = plugin.getConfig().getString("staff-prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.freeze")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("no-permission")));
            return true;
        }

        if (args.length < 1) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cError: /freeze (player)"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cError: Player is not online!"));
            return true;
        }

        if (frozenPlayers.containsKey(target)) {
            frozenPlayers.remove(target);
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aYou have been unfrozen!"));
            return true;
        }

        frozenPlayers.put(target, true);
        for (int i = 0; i < plugin.getConfig().getStringList("freeze-message").size(); i++) {
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getStringList("freeze-message").get(i)));
        }
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String prefix = plugin.getConfig().getString("prefix");
        if (frozenPlayers.containsKey(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou cannot move until you have been unfrozen!"));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String staffPrefix = plugin.getConfig().getString("staff-prefix");

        if (frozenPlayers.containsKey(p)) {
            if (p.hasPermission("spartanhub.admin")) {
                Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', staffPrefix + " " + p.getDisplayName() + " &chas logged out while frozen!"), "spartanhub.admin");
            }

            String banMessage = "";
            List<String> banMessages = plugin.getConfig().getStringList("freeze-ban-message");
            for (int i = 0; i < banMessages.size(); i++) {
                banMessage += banMessages.get(i);
            }
            banMessage = ChatColor.translateAlternateColorCodes('&', banMessage);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + banMessage);
            Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', staffPrefix + " " + p.getDisplayName() + " &cis now banned for &7Logging while frozen by &a&lCONSOLE!"), "spartanhub.admin");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            completions.add(p.getName());
        }

        return completions;
    }
}

