package me.spadelic.spspawn.Listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockPlace implements Listener {

    static SpartanHub plugin;
    private boolean blockPlacingDisabled;

    public BlockPlace(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        blockPlacingDisabled = config.getBoolean("block-place", false);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (blockPlacingDisabled) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("block-place-message")));
        }
    }

}
