package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    static SpartanHub plugin;

    public JoinListener(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        for(int i = 0; i < plugin.getConfig().getStringList("join-motd").size(); i++){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("join-motd").get(i)));
        }

    }

}
