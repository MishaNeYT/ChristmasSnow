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

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        getCommand("csnow").setExecutor(new Commands());

        SnowUpdates.onSnow();
        Utils.onLogger();
    }
}