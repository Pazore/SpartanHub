package me.spadelic.spspawn.commands;

import me.spadelic.spspawn.SpartanHub;
import me.spadelic.spspawn.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffChat implements CommandExecutor {

    private final SpartanHub plugin;

    public StaffChat(SpartanHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("prefix");
        Player player = (Player) sender;

        if (!player.hasPermission("spartanhub.staffchat")) {
            player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("no-permission")));
            return true;
        }

        if (args.length == 0) {
            if (plugin.getStaffChatEnabledPlayers().contains(player.getUniqueId())) {
                plugin.getStaffChatEnabledPlayers().remove(player.getUniqueId());
                player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("left-staffchat")));
            } else {
                plugin.getStaffChatEnabledPlayers().add(player.getUniqueId());
                player.sendMessage(CC.translate(prefix + plugin.getConfig().getString("entered-staffchat")));
            }
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        try {
            Connection connection = plugin.getDatabaseManager().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO staff_chat (player_name, message) VALUES (?, ?)");
            statement.setString(1, player.getName());
            statement.setString(2, message.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            player.sendMessage(CC.translate("&cAn error occurred while saving your message."));
            return true;
        }

        player.sendMessage(CC.translate("&aYour message has been sent to staff chat!"));

        return true;
    }
}
