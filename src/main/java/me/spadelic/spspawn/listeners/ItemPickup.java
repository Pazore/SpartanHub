package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemPickup implements Listener {

    static SpartanHub plugin;
    private boolean itemPickupDisabled;

    public ItemPickup(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        itemPickupDisabled = config.getBoolean("item-pickup", false);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (itemPickupDisabled) {
            e.setCancelled(false);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("item-pickup-message")));
        }
    }

}
