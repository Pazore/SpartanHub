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
    private boolean itemDropEnabled;

    public ItemDrop(SpartanHub plugin) {
        this.plugin = plugin;
//        FileConfiguration config = plugin.getConfig();
//        itemDropDisabled = config.getBoolean("item-drop", false);
//        itemDropEnabled = config.getBoolean("item-drop", true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (plugin.getConfig().getBoolean("item-drop", false)){
         e.setCancelled(false);
        } else {
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("item-drop-message")));
            e.setCancelled(true);
        }
    }

}
