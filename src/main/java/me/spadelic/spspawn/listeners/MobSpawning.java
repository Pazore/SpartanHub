package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawning implements Listener {

    static SpartanHub plugin;
    private boolean mobSpawnDisabled;

    public MobSpawning(SpartanHub plugin) {
        this.plugin = plugin;
//        FileConfiguration config = plugin.getConfig();
//        mobSpawnDisabled = config.getBoolean("mob-spawning", false);
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if (plugin.getConfig().getBoolean("mob-spawning", false)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

}
