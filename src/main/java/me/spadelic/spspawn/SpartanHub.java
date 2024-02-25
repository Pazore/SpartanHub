package me.spadelic.spspawn;

//import me.spadelic.spspawn.DataBase.DatabaseManager;
import me.spadelic.spspawn.commands.*;
import me.spadelic.spspawn.listeners.*;
import me.spadelic.spspawn.misc.Gamemode;
import me.spadelic.spspawn.misc.JoinMessage;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import sun.jvm.hotspot.debugger.win32.coff.DebugVC50SSSegName;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SpartanHub extends JavaPlugin {

    private Set<UUID> staffChatEnabledPlayers;

//    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        getLogger().info("SpartanHub Has Loaded");

        staffChatEnabledPlayers = new HashSet<>();

        getConfig().options().copyDefaults();
        saveDefaultConfig();
//
//        databaseManager = new DatabaseManager(this);
//        databaseManager.connect();

        SpartanHubCE spartanHubCE = new SpartanHubCE(this);

        getCommand("spartanhub").setExecutor(new SpartanHubCE(this));
        getCommand("setlobby").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("report").setExecutor(new Report());
        getCommand("vanish").setExecutor(new VanishCommand(this));

        registerListener(new SpawnListener(this));
        registerListener(new JoinMessage(this));
        registerListener(new JoinListener(this));
        registerListener(new JoinEffectListener(this));
        registerListener(new BlockBreak(this));
        registerListener(new MobSpawning(this));
        registerListener(new ItemPickup(this));
        registerListener(new PlayerPvP(this));
        registerListener(new ItemDrop(this));
        registerListener(new BlockPlace(this));
        registerListener(new FallDamage(this));
        registerListener(new DeathMessages(this));
    }

    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

//    public DatabaseManager getDatabaseManager() {
//        return databaseManager;
//    }

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
