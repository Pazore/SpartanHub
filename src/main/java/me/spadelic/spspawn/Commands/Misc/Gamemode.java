package me.spadelic.spspawn.Commands.Misc;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    static SpartanHub plugin;

    public Gamemode(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //normal
        String prefix = plugin.getConfig().getString("prefix");
        String onlyPlayers = plugin.getConfig().getString("only-players");
        String noPermission = plugin.getConfig().getString("no-permission");
        //game-modes
        String creative = plugin.getConfig().getString("gamemode-creative");
        String survival = plugin.getConfig().getString("gamemode-survival");
        String adventure = plugin.getConfig().getString("gamemode-adventure");
        String spectator = plugin.getConfig().getString("gamemode-spectator");

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + onlyPlayers));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.gamemode")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + noPermission));
            return true;
        } else {

            if (p.performCommand("gmc")) {
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + creative));
                return true;
            }

            if (p.performCommand("gms")) {
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + survival));
                return true;
            }

            if (p.performCommand("gma")) {
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + adventure));
                return true;
            }

            if (p.performCommand("gmsp")) {
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + spectator));
                return true;
            }

            return true;
        }
    }
}
