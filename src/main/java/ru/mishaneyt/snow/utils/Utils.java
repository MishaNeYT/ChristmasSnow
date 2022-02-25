package ru.mishaneyt.snow.utils;

import org.bukkit.ChatColor;
import ru.mishaneyt.snow.Main;

public class Utils {

    public static String colorChat(String msg) {
        return ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString(msg));
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void onLogger() {
        Main.getInstance().getLogger().info("");
        Main.getInstance().getLogger().info("§a---------------------------------------------------------------");
        Main.getInstance().getLogger().info("§aChristmasSnow - плагин успешно включен! Создатель MishaNeYT.");
        Main.getInstance().getLogger().info("§a---------------------------------------------------------------");
        Main.getInstance().getLogger().info("");
    }
}