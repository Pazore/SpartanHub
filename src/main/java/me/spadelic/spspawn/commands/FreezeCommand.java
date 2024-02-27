package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreezeCommand implements TabExecutor, Listener {

    private final SpartanHub plugin = SpartanHub.getPlugin();
    private final Map<Player, Boolean> frozenPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(prefix + plugin.getConfig().getString("only-players")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("spartanhub.freeze")) {
            p.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        }

        if (args.length < 1) {
            p.sendMessage(CC.translate(prefix + "&cError: /freeze (player)"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            p.sendMessage(CC.translate(prefix + "&cError: Player is not online!"));
            return true;
        }

        if (frozenPlayers.containsKey(target)) {
            frozenPlayers.remove(target);
            target.sendMessage(CC.translate(prefix + "&aYou have been unfrozen!"));
            return true;
        }

        frozenPlayers.put(target, true);
        for(int i = 0; i < plugin.getConfig().getStringList("freeze-message").size(); i++){
            target.sendMessage(CC.translate(prefix + plugin.getConfig().getStringList("freeze-message").get(i)));
        }
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String prefix = plugin.getConfig().getString("prefix");
        if (frozenPlayers.containsKey(p)) {
            e.setCancelled(true);
            p.sendMessage(CC.translate(prefix + "&cYou cannot move until you have been unfrozen!"));
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