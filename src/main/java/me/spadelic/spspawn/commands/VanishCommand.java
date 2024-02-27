package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final SpartanHub plugin = SpartanHub.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        String staffPrefix = plugin.getConfig().getString("staff-prefix");
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
        } else {
            if (!(plugin.vanished.contains(p))) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("spartanhub.bypassvanish")) {
                        all.hidePlayer(p);
                    } else {
                        all.sendMessage(CC.translate(staffPrefix + p.getDisplayName() + " &7has &a&lVANISHED"));
                    }
                }
                plugin.vanished.add(p);
                p.sendMessage(CC.translate(prefix + vanish));
            } else {
                for (Player all : Bukkit.getOnlinePlayers()){
                    if (!all.hasPermission("spartanhub.bypassvanish")) {
                        all.showPlayer(p);
                    } else {
                        all.sendMessage(CC.translate(staffPrefix + p.getDisplayName() + " &7has &c&lUNVANISHED"));
                    }
                }
                plugin.vanished.remove(p);
                p.sendMessage(CC.translate(prefix + unvanish));

            }

            return true;
        }
    }
}
