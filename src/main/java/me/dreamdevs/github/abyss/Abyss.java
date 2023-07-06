package me.dreamdevs.github.abyss;

import me.dreamdevs.github.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Abyss {
    private static Map<Player, Integer> map = new HashMap<Player, Integer>();
    private static int rows = 5;
    public static Inventory[] inv = new Inventory[200];

    public static void open() {
        Pages.reset();
        Cleaner.clear();
        Abyss.inv[0] = Bukkit.createInventory(null, rows * 9, Settings.otchlanguiname);
        Map<Map<UUID, ItemStack>, Integer> items = Cleaner.getItemList();
        int amount = 0;
        for (Map<UUID, ItemStack> maps : items.keySet()) {
            for(UUID uuid : maps.keySet()) {
                amount += items.get(maps).intValue();
                Pages.setSlot(maps.get(uuid), items.get(maps));
            }
        }
        Bukkit.broadcastMessage(Settings.otchlanbroadcast1.replace("{AMOUNT}", ""+amount));
    }

    public static Inventory[] getInv() {
        return inv;
    }

    public static int getRows() {
        return rows;
    }

    public static void setRows(int a) {
        rows = a;
    }

    public static Map<Player, Integer> getMap() {
        return map;
    }
}
