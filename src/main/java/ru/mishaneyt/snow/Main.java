package ru.mishaneyt.snow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
    public static Main plugin;

    private final FileConfiguration config = getConfig();

    /* ******************************************************************************************* */

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("csnow")).setExecutor(this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, this::Updates, 20L, 0L);
        Bukkit.getPluginManager().registerEvents(this, this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "ChristmasSnow - has been enabled, Author: Kai_Rivson");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------------------------------------------");

        saveConfig();
    }

    @Override
    public void onDisable() { saveConfig(); }

    /* ******************************************************************************************* */


    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        if (Main.plugin.getConfig().getBoolean("Settings.TitlesOnJoin")) {
            Player player = event.getPlayer();
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.plugin.getConfig()
                    .getString("TitlesOnJoin.Title"))), ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.plugin.getConfig()
                    .getString("TitlesOnJoin.Subtitle"))), Main.plugin.getConfig()
                    .getInt("TitlesOnJoin.FadeIn"), Main.plugin.getConfig()
                    .getInt("TitlesOnJoin.Stay"), Main.plugin.getConfig()
                    .getInt("TitlesOnJoin.FadeOut"));
        }
    }


    public void Updates() {
        if (getConfig().getBoolean("Settings.SnowToSpawn")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), this.config.getInt("SnowToSpawn.Count"), this.config.getInt("SnowToSpawn.RadiusX"), this.config.getInt("SnowToSpawn.RadiusY"), this.config.getInt("SnowToSpawn.RadiusZ"), this.config.getInt("SnowToSpawn.Speed"));
            }
        }
    }

    /* ******************************************************************************************* */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("csnow")) {
            if (sender.hasPermission("csnow.admin")) {
                if (args.length == 0) {
                    if (sender.hasPermission("csnow.admin")) {
                        PluginDescriptionFile pdf = getDescription();
                        for (String helpMsg : getConfig().getStringList("Messages.Help")) {
                            helpMsg = helpMsg.replaceAll("%version%", pdf.getVersion());
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " " + helpMsg));
                        }
                    }
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("reload"))
                    if (sender.hasPermission("csnow.admin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("Messages.Reload"))));
                        reloadConfig();
                        saveConfig();
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("Messages.noPerms"))));
                        return true;
                    }
            }
        }
        return false;
    }
}