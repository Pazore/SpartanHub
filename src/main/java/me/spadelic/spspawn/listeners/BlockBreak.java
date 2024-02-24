package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    static SpartanHub plugin;
    private boolean blockBreakingDisabled;
    private boolean blockBreakingEnabled;

    public BlockBreak(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        blockBreakingDisabled = config.getBoolean("block-break", false);
        blockBreakingEnabled = config.getBoolean("block-break", true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (blockBreakingDisabled) {
            e.setCancelled(false);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("block-break-message")));
        } else {
            if (blockBreakingEnabled) {
                e.setCancelled(true);
            }
        }
    }
}
