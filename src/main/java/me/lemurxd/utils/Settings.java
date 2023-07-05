package me.lemurxd.utils;

import me.lemurxd.Main;

import java.util.HashMap;

public class Settings {
    public static String otchlandenymessage;
    public static String otchlanpermissiondenymessage;
    public static String otchlanlastpagedontexist;
    public static String otchlannextpagedontexist;
    public static String otchlanbroadcast1;
    public static String otchlanguiname;
    public static String permissionpages;
    public static HashMap<Integer, String> warnslist = new HashMap<>();
    public static HashMap<Integer, String> warnscloselist = new HashMap<>();
    public static String otchlanclearbroadcast;
    public static int timer;
    public static int rows;
    public static int timerend;

    public Settings(Main plugin) {
        plugin.reloadConfig();
        warnslist.clear();
        warnscloselist.clear();
        otchlandenymessage = plugin.getConfig().getString("Message-deny").replace("&", "§");
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
