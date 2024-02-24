package me.spadelic.spspawn.misc;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    private final SpartanHub plugin;

    public Gamemode(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.gamemode")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + plugin.getConfig().getString("no-permission")));
            return true;
        } else {

            if (args.length != 1) {
                p.sendMessage(ChatColor.RED + "Error: /gm(c,s,a,sp)");
                return true;
            }

            GameMode mode;
            String modeString = args[0].toLowerCase();
            String prefix = plugin.getConfig().getString("prefix");
            String messageKey = "gamemode-" + modeString;

            switch (modeString) {
                case "gmc":
                    mode = GameMode.CREATIVE;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString(messageKey)));
                    break;
                case "gms":
                    mode = GameMode.SURVIVAL;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString(messageKey)));
                    break;
                case "gma":
                    mode = GameMode.ADVENTURE;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString(messageKey)));
                    break;
                case "gmsp":
                    mode = GameMode.SPECTATOR;
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString(messageKey)));
                    break;
                default:
                    p.sendMessage(ChatColor.RED + "Invalid gamemode! Usage: /gamemode <gmc|gmsp|gms|gma>");
                    return true;
            }

            p.setGameMode(mode);
            return true;
        }
    }
}
