package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessages implements Listener {

    static SpartanHub plugin;

    public DeathMessages(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        String prefix = plugin.getConfig().getString("prefix");
        e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("death-message")));
    }

}
