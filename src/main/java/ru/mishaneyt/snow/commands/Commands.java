package ru.mishaneyt.snow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import ru.mishaneyt.snow.Main;
import ru.mishaneyt.snow.utils.Utils;

public class Commands implements CommandExecutor {

    public Commands(Main main) {
        main.getCommand("csnow").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.ONLY_PLAYER);
            return true;
        }
        if (!sender.hasPermission("csnow.admin")) {
            sender.sendMessage(Utils.NO_PERM);
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            PluginDescriptionFile version = Main.getInstance().getDescription();

            for (String helpMsg : Main.getInstance().getConfig().getStringList("Messages.Help"))
                p.sendMessage(Utils.color(helpMsg).replace("%version%", version.getVersion()));
        }

        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Main.getInstance().reloadConfig();
                p.sendMessage(Utils.RELOAD);
            }

            else if (args[0].equalsIgnoreCase("toggle")) {
                Utils.toggle(p);

            } else p.sendMessage(Utils.ERROR);
        } else p.sendMessage(Utils.ERROR);
        return false;
    }
}