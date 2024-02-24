package me.spadelic.spspawn.commands.misc;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinMessage implements Listener {

    static SpartanHub plugin;

    public JoinMessage(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        String joinMessage = plugin.getConfig().getString("join-message").replace("%player%", p.getDisplayName());
        String joinMessage2 = plugin.getConfig().getString("join-message").replace("%username%", p.getName());
            String prefix = plugin.getConfig().getString("prefix");

            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix + joinMessage));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        String leaveMessage = plugin.getConfig().getString("leave-message").replace("%player%", p.getDisplayName());
        String leaveMessage2 = plugin.getConfig().getString("leave-message").replace("%username%", p.getName());
        String prefix = plugin.getConfig().getString("prefix");

        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', prefix + leaveMessage));

    }

}
