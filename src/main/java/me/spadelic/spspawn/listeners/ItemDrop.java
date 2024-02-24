package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    static SpartanHub plugin;
    private boolean itemDropDisabled;

    public ItemDrop(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        itemDropDisabled = config.getBoolean("item-drop", false);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (itemDropDisabled) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("item-drop-message")));
        }
    }

}
