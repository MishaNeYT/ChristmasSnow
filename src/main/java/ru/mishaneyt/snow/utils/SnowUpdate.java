package ru.mishaneyt.snow.utils;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.mishaneyt.snow.Main;

public class SnowUpdate {

    public static void start() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player all : Bukkit.getServer().getOnlinePlayers())
                    if (Utils.ENABLE) {
                        FileConfiguration config = Main.getInstance().getConfig();
                        String world = all.getLocation().getWorld().getName();

                        if (config.getStringList("Settings.EnableWorld").contains(world)) {
                            all.spawnParticle(Particle.FIREWORKS_SPARK, all.getLocation(), Utils.COUNT, Utils.RADIUS_X, Utils.RADIUS_Y, Utils.RADIUS_Z, 0.0);
                        }
                    }
            }
        }.runTaskTimer(Main.getInstance(), 20, 0);
    }
}