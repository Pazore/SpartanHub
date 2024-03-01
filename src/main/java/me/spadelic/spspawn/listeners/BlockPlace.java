package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    static SpartanHub plugin;
//    private boolean blockPlacingDisabled;
//    private boolean blockPlacingEnabled;

    public BlockPlace(SpartanHub plugin) {
        this.plugin = plugin;
//        FileConfiguration config = plugin.getConfig();
//        blockPlacingDisabled = config.getBoolean("block-place", false);
//        blockPlacingEnabled = config.getBoolean("block-place", true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if (plugin.getConfig().getBoolean("block-place", false)){
            e.setCancelled(false);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
        } else {
            e.setCancelled(true);
            String prefix = plugin.getConfig().getString("prefix");
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("block-place-message")));
        }
    }
}
