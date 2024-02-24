package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {

    static SpartanHub plugin;

    public FallDamage(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!plugin.getConfig().getBoolean("fall-damage")) {
                EntityDamageEvent.DamageCause damageCause = e.getCause();
                if (damageCause.equals(EntityDamageEvent.DamageCause.FALL)) {
                    e.setCancelled(false);
                } else {
                        e.setCancelled(true);
                    }
                }
            }
        }

