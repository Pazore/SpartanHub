package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements TabExecutor {

    private ArrayList<Player> flyingPlayers = new ArrayList<>();

    SpartanHub plugin = SpartanHub.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.fly")) {
            p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        } else if (args.length == 0) {
            if (flyingPlayers.contains(p)) {
                flyingPlayers.remove(p);
                p.setAllowFlight(false);
                p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("fly-untoggled")));
            } else {
                if (flyingPlayers.contains(p)) {
                    flyingPlayers.add(p);
                    p.setAllowFlight(true);
                    p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("fly-toggled")));
                }
                return true;
            }
        } else if (args.length == 1) {
            if (!p.hasPermission("spartanhub.fly.others")) {
                p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
                return true;
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (flyingPlayers.contains(p)) {
                flyingPlayers.remove(t);
                t.setAllowFlight(false);
                t.sendMessage(CC.translate(prefix + plugin.getConfig().getString("fly-others-untoggled")));
                p.sendMessage(CC.translate(prefix + "&aYou have disabled flight for %vault_prefix%%player_name%"));
            } else {
                flyingPlayers.add(p);
                t.setAllowFlight(true);
                t.sendMessage(CC.translate(prefix + plugin.getConfig().getString("fly-others-toggled")));
                p.sendMessage(CC.translate(prefix + "&aYou have enabled flight for %vault_prefix%%player_name%"));
            }
            return true;
        }



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()){
            completions.add(p.getName());

            return completions;
        }


        return null;
    }
}
