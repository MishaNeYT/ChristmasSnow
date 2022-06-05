package ru.mishaneyt.snow;

import org.bukkit.plugin.java.JavaPlugin;
import ru.mishaneyt.snow.commands.Commands;
import ru.mishaneyt.snow.commands.CommandsTab;
import ru.mishaneyt.snow.utils.SnowUpdate;
import ru.mishaneyt.snow.utils.Utils;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        new Commands(this);
        new CommandsTab(this);

        Utils.check(this);
        Utils.load();

        SnowUpdate.start();
    }

    public static Main getInstance() {
        return instance;
    }
}