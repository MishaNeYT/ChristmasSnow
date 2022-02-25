package ru.mishaneyt.snow.utils;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.mishaneyt.snow.Main;

public class SnowUpdates {

    public static void onSnow() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers())
                    if (Main.getInstance().getConfig().getBoolean("Settings.SnowToSpawn")) {
                        if (Main.getInstance().getConfig().getStringList("Settings.EnableWorld").contains(player.getLocation().getWorld().getName())) {
                            player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(),
                                    Main.getInstance().getConfig().getInt("SnowToSpawn.Count"),
                                    Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusX"),
                                    Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusY"),
                                    Main.getInstance().getConfig().getInt("SnowToSpawn.RadiusZ"), 0.0);
                        }
                    }
            }
        }.runTaskTimer(Main.getInstance(), 20, 0);
    }
}