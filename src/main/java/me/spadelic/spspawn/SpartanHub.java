package me.spadelic.spspawn;

import me.spadelic.spspawn.misc.Gamemode;
import me.spadelic.spspawn.misc.JoinMessage;
import me.spadelic.spspawn.commands.SetSpawn;
import me.spadelic.spspawn.commands.SpartanHubCE;
import me.spadelic.spspawn.commands.SpawnCommand;
import me.spadelic.spspawn.listeners.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import static java.awt.SystemColor.info;

public final class SpartanHub extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SpartanHub Has Loaded");

        saveDefaultConfig();
        getConfig().options().copyDefaults();

        CommandExecutor spartanHubCE = new SpartanHubCE(this);

        getCommand("spartanhub").setExecutor(spartanHubCE);
        getCommand("setlobby").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
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
}