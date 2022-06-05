package ru.mishaneyt.snow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import ru.mishaneyt.snow.Main;

import java.util.ArrayList;
import java.util.List;

public class CommandsTab implements TabCompleter {

    public CommandsTab(Main main) {
        main.getCommand("csnow").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> line = new ArrayList<>();

        if (args.length == 1) {
            line.add("toggle");
            line.add("reload");

            return line;
        }
        return null;
    }
}