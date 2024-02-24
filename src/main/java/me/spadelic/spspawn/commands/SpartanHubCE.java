package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.help.AliasCommand;
import me.spadelic.spspawn.help.HelpCommand;
import me.spadelic.spspawn.SpartanHub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpartanHubCE implements CommandExecutor {

    private final SetSpawn setSpawnCommand;
    private final SpawnCommand spawnCommand;
    private final ConfigReload configReloadCommand;
    private final HelpCommand helpCommand;
    private final AliasCommand aliasCommand;

    public SpartanHubCE(SpartanHub plugin) {
        this.setSpawnCommand = new SetSpawn(plugin);
        this.spawnCommand = new SpawnCommand(plugin);
        this.configReloadCommand = new ConfigReload(plugin);
        this.helpCommand = new HelpCommand(plugin);
        this.aliasCommand = new AliasCommand();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            return helpCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("reload")) {
            return configReloadCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("alias")) {
            return aliasCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("gift")) {
            } else {

            if (args[0].equalsIgnoreCase("setspawn")) {
                return setSpawnCommand.onCommand(sender, command, label, args);
            } else if (args[0].equalsIgnoreCase("spawn")) {
                return spawnCommand.onCommand(sender, command, label, args);
            }
        }
        return false;
    }
}
