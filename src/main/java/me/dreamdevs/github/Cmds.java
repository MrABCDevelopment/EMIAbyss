package me.dreamdevs.github;

import me.dreamdevs.github.abyss.Abyss;
import me.dreamdevs.github.utils.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmds implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("abyss")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("open") && sender.hasPermission("otchlan.admin")) {
                Abyss.open();
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("otchlan.admin")) {
                new Settings(Main.getInstance());
                sender.sendMessage(ChatColor.GREEN+"Done!");
                return true;
            }
            if (sender instanceof Player) {
                Player p = (Player)sender;
                if (p.hasPermission("abyss.open")) {
                    if (Abyss.getInv()[0] != null) {
                        Abyss.getMap().put(p, 0);
                        p.openInventory(Abyss.getInv()[0]);
                        return true;
                    }
                    p.sendMessage(Settings.otchlandenymessage);
                    return true;
                }
                p.sendMessage(Settings.otchlanpermissiondenymessage);
                return true;
            }
        }
        return false;
    }
}
