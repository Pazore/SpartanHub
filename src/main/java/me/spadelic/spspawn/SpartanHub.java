package me.spadelic.spspawn;

//import me.spadelic.spspawn.DataBase.DatabaseManager;
import me.spadelic.spspawn.commands.*;
import me.spadelic.spspawn.listeners.*;
import me.spadelic.spspawn.misc.Gamemode;
import me.spadelic.spspawn.misc.JoinMessage;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SpartanHub extends JavaPlugin {

    private Set<UUID> staffChatEnabledPlayers;

    private me.spadelic.spspawn.DataBase.DatabaseManager databaseManager;

    public static ArrayList<Player> vanished = new ArrayList<>();

    private static SpartanHub plugin;

    @Override
    public void onEnable() {
        getLogger().info("SpartanHub Has Loaded");

        plugin = this;

        staffChatEnabledPlayers = new HashSet<>();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        databaseManager = new me.spadelic.spspawn.DataBase.DatabaseManager(this);
        databaseManager.connect();

        SpartanHubCE spartanHubCE = new SpartanHubCE(this);

        getCommand("spartanhub").setExecutor(new SpartanHubCE(this));
        getCommand("setlobby").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("staffchat").setExecutor(new StaffChatCommand(this, staffChatEnabledPlayers, databaseManager));
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("report").setExecutor(new ReportCommand(this, databaseManager));
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());

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

        new CC();

    }

    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public me.spadelic.spspawn.DataBase.DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public Set<UUID> getStaffChatEnabledPlayers() {
        return staffChatEnabledPlayers;
    }

    public static SpartanHub getPlugin() {
        return plugin;
    }
}
