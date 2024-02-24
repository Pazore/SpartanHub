package me.spadelic.spspawn.Listeners;

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

    public BlockBreak(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        blockBreakingDisabled = config.getBoolean("block-break", false);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (blockBreakingDisabled) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            String prefix = plugin.getConfig().getString("prefix");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + plugin.getConfig().getString("block-break-message")));
        }
    }

}
