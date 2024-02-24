package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerPvP implements Listener {

    static SpartanHub plugin;
    public boolean onPvPDisabled;

    public PlayerPvP(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        onPvPDisabled = config.getBoolean("pvp", false);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e) {
        if (onPvPDisabled) {
            if (e.getEntity() instanceof Player) {
                e.setCancelled(true);
                String prefix = plugin.getConfig().getString("prefix");
                Player p = (Player) e.getEntity();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("pvp-message")));
            }
        }
    }
}
