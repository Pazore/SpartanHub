package me.spadelic.spspawn.commands.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CC {

    public static String translate(String message){

        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
