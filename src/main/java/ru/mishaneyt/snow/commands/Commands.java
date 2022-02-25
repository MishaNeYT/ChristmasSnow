package ru.mishaneyt.snow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import ru.mishaneyt.snow.Main;
import ru.mishaneyt.snow.utils.Utils;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.colorChat("Messages.OnlyPlayer"));
            return false;
        }

        if (!sender.hasPermission("csnow.admin")) {
            sender.sendMessage(Utils.colorChat("Messages.noPerm"));
            return false;
        }

        if (command.getName().equalsIgnoreCase("csnow")) {
            if (args.length == 0) {
                PluginDescriptionFile version = Main.getInstance().getDescription();
                for (String helpMsg : Main.getInstance().getConfig().getStringList("Messages.Help")) {
                    sender.sendMessage(Utils.color(helpMsg).replace("%version%", version.getVersion()));
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                Main.getInstance().reloadConfig();
                Main.getInstance().saveConfig();
                sender.sendMessage(Utils.colorChat("Messages.Reload"));
                return false;
            } else {
                sender.sendMessage(Utils.colorChat("Messages.ErrorArgs"));
                return true;
            }
        }
        return false;
    }
}