package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class VanishCommand implements CommandExecutor {

    static SpartanHub plugin;

    public VanishCommand(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        String vanish = plugin.getConfig().getString("vanish-message");
        String unvanish = plugin.getConfig().getString("unvanish-message");
        String vanishSuffix = plugin.getConfig().getString("vanish-suffix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.vanish")) {
            p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        }

        if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.setDisplayName(p.getName());
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                if (!onlinePlayer.equals(p)) {
                    removeFromTabList(onlinePlayer, p);
                }
            }
            p.sendMessage(CC.translate(prefix + unvanish));
        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));
            p.setDisplayName(p.getName() + vanishSuffix);
            for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                if (!onlinePlayer.equals(p)) {
                    addToTabList(onlinePlayer, p);
                }
            }
            p.sendMessage(CC.translate(prefix + vanish));
            addToTabList(p, p);
        }

        return true;
    }

    private void removeFromTabList(Player viewer, Player vanishedPlayer) {
        Scoreboard scoreboard = viewer.getScoreboard();
        Objective objective = scoreboard.getObjective(DisplaySlot.PLAYER_LIST);
        if (objective != null) {
            objective.getScore(vanishedPlayer.getName()).setScore(9999);
        }
    }

    private void addToTabList(Player viewer, Player vanishedPlayer) {
        Scoreboard scoreboard = viewer.getScoreboard();
        Objective objective = scoreboard.getObjective(DisplaySlot.PLAYER_LIST);
        if (objective != null) {
            objective.getScore(vanishedPlayer.getName()).setScore(-1);
        }
    }
}
