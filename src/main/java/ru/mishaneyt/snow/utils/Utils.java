package ru.mishaneyt.snow.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import ru.mishaneyt.snow.Main;

import java.io.File;

public class Utils {
    public static int COUNT;
    public static int RADIUS_X;
    public static int RADIUS_Y;
    public static int RADIUS_Z;

    public static Boolean ENABLE;
    public static String RELOAD;
    public static String ONLY_PLAYER;
    public static String ERROR;
    public static String TOGGLE_ENABLE;
    public static String TOGGLE_DISABLE;
    public static String NO_PERM;

    public static String color(String s) {
        return s.replace("&", "§");
    }

    public static void check(Main main) {
        File config = new File(main.getDataFolder(), "config.yml");

        if (!config.exists()) main.saveDefaultConfig();
    }

    public static void toggle(Player p) {
        FileConfiguration config = Main.getInstance().getConfig();
        PluginManager pm = Bukkit.getPluginManager();
        String enable = "Settings.Enable";

        if (Utils.ENABLE == Boolean.TRUE) {
            config.set(enable, Boolean.FALSE);
            p.sendMessage(TOGGLE_DISABLE);
        } else {
            config.set(enable, Boolean.TRUE);
            p.sendMessage(TOGGLE_ENABLE);
        }

        Main.getInstance().saveConfig();

        pm.disablePlugin(Main.getInstance());
        pm.enablePlugin(Main.getInstance());
    }

    public static void load() {
        FileConfiguration config = Main.getInstance().getConfig();

        COUNT = config.getInt("Settings.Options.Count");
        RADIUS_X = config.getInt("Settings.Options.RadiusX");
        RADIUS_Y = config.getInt("Settings.Options.RadiusY");
        RADIUS_Z = config.getInt("Settings.Options.RadiusZ");

        ENABLE = config.getBoolean("Settings.Enable");
        RELOAD = Utils.color(config.getString("Messages.Reload"));
        ONLY_PLAYER = Utils.color(config.getString("Messages.OnlyPlayer"));
        ERROR = Utils.color(config.getString("Messages.Error"));
        TOGGLE_ENABLE = Utils.color(config.getString("Messages.Toggle.Enable"));
        TOGGLE_DISABLE = Utils.color(config.getString("Messages.Toggle.Disable"));
        NO_PERM = Utils.color(config.getString("Messages.NoPerm"));
    }
}