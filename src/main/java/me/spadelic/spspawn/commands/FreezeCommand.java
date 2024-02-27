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

public class FreezeCommand implements TabExecutor {

    private final SpartanHub plugin = SpartanHub.getPlugin();

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
        } else {

            if (args.length < 1){
                p.sendMessage(CC.translate(prefix + "&cError: /freeze (player)"));
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || target.isOnline()){
                target.sendMessage(CC.translate(prefix + "&cError: Player is not online!"));
                return true;
            } else {
                for(int i = 0; i < plugin.getConfig().getStringList("freeze-message").size(); i++){
                    target.sendMessage(CC.translate(prefix + plugin.getConfig().getStringList("freeze-message").get(i)));
                }
            }


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
