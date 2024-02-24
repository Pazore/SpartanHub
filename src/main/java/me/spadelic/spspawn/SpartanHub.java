package me.spadelic.spspawn;

import me.spadelic.spspawn.commands.SetSpawn;
import me.spadelic.spspawn.commands.SpartanHubCE;
import me.spadelic.spspawn.commands.SpawnCommand;
import me.spadelic.spspawn.commands.StaffChat;
import me.spadelic.spspawn.listeners.*;
import me.spadelic.spspawn.misc.Gamemode;
import me.spadelic.spspawn.misc.JoinMessage;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SpartanHub extends JavaPlugin {

    private Set<UUID> staffChatEnabledPlayers;

    @Override
    public void onEnable() {
        getLogger().info("SpartanHub Has Loaded");

        staffChatEnabledPlayers = new HashSet<>();

        SpartanHubCE spartanHubCE = new SpartanHubCE(this);

        getCommand("spartanhub").setExecutor(new SpartanHubCE(this));
        getCommand("setlobby").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));

        getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinMessage(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinEffectListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new MobSpawning(this), this);
        getServer().getPluginManager().registerEvents(new ItemPickup(this), this);
        getServer().getPluginManager().registerEvents(new PlayerPvP(this), this);
        getServer().getPluginManager().registerEvents(new ItemDrop(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new FallDamage(this), this);
        getServer().getPluginManager().registerEvents(new DeathMessages(this), this);


    }
    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public Set<UUID> getStaffChatEnabledPlayers() {
        return staffChatEnabledPlayers;
    }

    public void sendStaffChatMessage(Player sender, String message) {
        for (UUID playerId : staffChatEnabledPlayers) {
            Player p = getServer().getPlayer(playerId);
            if (p != null) {
                p.sendMessage(CC.translate("&b[S] " + sender.getDisplayName() + "&f: &3" + message));
            }
        }
    }
}
