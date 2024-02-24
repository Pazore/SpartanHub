package me.spadelic.spspawn.Listeners;

import me.spadelic.spspawn.SpartanHub;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener {

    static SpartanHub plugin;

    public SpawnListener(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        String prefix = plugin.getConfig().getString("prefix");

        if (!player.hasPlayedBefore()){
            Location location = (Location) plugin.getConfig().get("lobby");

            if (location != null){

                player.teleport(location);

                //SEPARATE

                if (player.hasPlayedBefore()){
                    Location location2 = (Location) plugin.getConfig().get("lobby");

                    if (location2 != null){

                        player.teleport(location);

                    }
                }

            }
        }

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){

        Player player = e.getPlayer();
        //when a player dies, respawn them at the spawn location if its set
        Location location = (Location) plugin.getConfig().get("lobby");

        if (location == null){
            e.setRespawnLocation(location);
        }

    }}

