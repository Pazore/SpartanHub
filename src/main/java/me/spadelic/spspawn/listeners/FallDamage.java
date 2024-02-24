package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {

    static SpartanHub plugin;
    public boolean fallDamageDisabled;

    public FallDamage(SpartanHub plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        fallDamageDisabled = config.getBoolean("fall-damage", false);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (fallDamageDisabled) {
                EntityDamageEvent.DamageCause damageCause = e.getCause();
                if (damageCause.equals(EntityDamageEvent.DamageCause.FALL)) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
