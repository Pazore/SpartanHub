package me.spadelic.spspawn.commands.main;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements TabExecutor {

    static SpartanHub plugin;

    public SpawnCommand(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            Location location = (Location) plugin.getConfig().get("spawn");

            String prefix = plugin.getConfig().getString("prefix");

            if (plugin.getConfig().getInt("spawn-timer") == 0) {
                spawn(p, location, prefix);
            } else if (plugin.getConfig().getInt("spawn-timer") > 0) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        //p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aYou will be teleported to spawn in {0} seconds!"));
                        spawn(p, location, prefix);
                    }
                }.runTaskLater(plugin, 20 * plugin.getConfig().getInt("spawn-timer"));
            }

        } else {

            String prefix = plugin.getConfig().getString("prefix");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("only-players")));
        }
        return true;
    }

    public void spawn(Player p, Location location, String prefix) {

        if (location != null) {
            p.teleport(location);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("teleported-to-spawn")));
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("no-spawn-location")));

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("only-players")));
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.playerlist")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());

                completions.add("*");
                completions.add("@a");
                completions.add("all");
            }

            return completions;

        }
        return null;
    }
}