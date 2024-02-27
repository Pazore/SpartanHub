/*package me.spadelic.spspawn.utils;

import org.bukkit.ChatColor;

public class HexCode {

    public static String hexToColor(String hexCode) {
        if (hexCode.startsWith("#")) {
            hexCode = hexCode.substring(1);
        }

        if (hexCode.length() != 6) {
            return ChatColor.RESET.toString();
        }

        return mapHexToColor(hexCode);
    }

    private static String mapHexToColor(String hexCode) {
        String[] colorCodes = {"§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f"};
        int colorValue = Integer.parseInt(hexCode, 16);
        int index = Math.round(colorValue / (255 / (colorCodes.length - 1)));
        if (index >= colorCodes.length) {
            index = colorCodes.length - 1;
        } else if (index < 0) {
            index = 0;
        }
        return colorCodes[index];
    }
}*/
