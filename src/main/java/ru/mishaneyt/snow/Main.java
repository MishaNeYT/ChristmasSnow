package ru.mishaneyt.snow;

import org.bukkit.plugin.java.JavaPlugin;
import ru.mishaneyt.snow.commands.Commands;
import ru.mishaneyt.snow.utils.SnowUpdates;
import ru.mishaneyt.snow.utils.Utils;

public class Main extends JavaPlugin {
    public static Main instance;
    public static Main getInstance() {
        return instance;
    }

    public int Count;
    public int RadiusX;
    public int RadiusY;
    public int RadiusZ;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        Count = Main.getInstance().getConfig().getInt("SnowToSpawn.Count");
        RadiusX = Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusX");
        RadiusY = Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusY");
        RadiusZ = Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusZ");

        getCommand("csnow").setExecutor(new Commands());

        SnowUpdates.onSnow();
        Utils.onLogger();
    }
}