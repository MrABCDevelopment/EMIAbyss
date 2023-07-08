package me.dreamdevs.github.utils;

import me.dreamdevs.github.Main;
import me.dreamdevs.github.abyss.Abyss;
import me.dreamdevs.github.abyss.Pages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Settings {
    public static String otchlandenymessage;
    public static String otchlanpermissiondenymessage;
    public static String otchlanlastpagedontexist;
    public static String otchlannextpagedontexist;
    public static String otchlanbroadcast1;
    public static String otchlanguiname;
    public static String permissionpages;
    public static String itemloreup;
    public static String itemloredown;
    public static String itemname;
    public static HashMap<Integer, String> warnslist = new HashMap<>();
    public static HashMap<Integer, String> warnscloselist = new HashMap<>();
    public static String otchlanclearbroadcast;
    public static int timer;
    public static int rows;
    public static int timerend;

    public Settings(Main plugin) {
        plugin.reloadConfig();
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
        if(!(Abyss.getInv()[0] == null)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.getOpenInventory() != null && all.getOpenInventory().getTitle().equals(Settings.otchlanguiname)) {
                    all.closeInventory();
                }
            }
            Pages.reset();
            Abyss.getInv()[0].clear();
            Abyss.getInv()[0] = null;
        }
        warnslist.clear();
        warnscloselist.clear();
        otchlandenymessage = plugin.getConfig().getString("Message-deny").replace("&", "§");
        itemloreup = plugin.getConfig().getString("Lore-up").replace("&", "§");
        itemloredown = plugin.getConfig().getString("Lore-down").replace("&", "§");
        itemname = plugin.getConfig().getString("item-name").replace("&", "§");
        otchlanpermissiondenymessage = plugin.getConfig().getString("Message-otchlanpermissiondeny").replace("&", "§");
        otchlanlastpagedontexist = plugin.getConfig().getString("Message-last-page-doasnt-exist").replace("&", "§");
        otchlannextpagedontexist = plugin.getConfig().getString("Message-next-page-doasnt-exist").replace("&", "§");
        otchlanbroadcast1 = plugin.getConfig().getString("Broadcast").replace("&", "§");
        otchlanclearbroadcast = plugin.getConfig().getString("Broadcast-otchlanclear").replace("&", "§");
        otchlanguiname = plugin.getConfig().getString("Otchlanguiname").replace("&", "§");
        permissionpages = plugin.getConfig().getString("Message-page-permission").replace("&", "§");
        for(String s : plugin.getConfig().getConfigurationSection("Abyss-warns").getKeys(false)) {
            warnslist.put(Integer.parseInt(s), plugin.getConfig().getString("Abyss-warns." + s + ".Message").replace("&", "§"));
        }
        for(String s : plugin.getConfig().getConfigurationSection("Abyss-close-warns").getKeys(false)) {
            warnscloselist.put(Integer.parseInt(s), plugin.getConfig().getString("Abyss-close-warns." + s + ".Message").replace("&", "§"));
        }
        timer = plugin.getConfig().getInt("timer");
        rows = plugin.getConfig().getInt("rows");
        timerend = plugin.getConfig().getInt("Time-end");

    }

}
