package me.spadelic.spspawn.listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinEffectListener implements Listener {

    private final SpartanHub plugin;

    public JoinEffectListener(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEffect(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        FileConfiguration config = plugin.getConfig();
        boolean applySpeedEffect = config.getBoolean("apply-speed-effect", true);

        if (!plugin.getConfig().getString("spawn").isEmpty()) {
            if (applySpeedEffect) {
                // Apply permanent Speed II potion effect
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
            }
        }
    }
}
