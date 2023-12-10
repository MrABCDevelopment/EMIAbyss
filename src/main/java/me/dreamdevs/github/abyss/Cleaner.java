package me.dreamdevs.github.abyss;

import me.dreamdevs.github.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Cleaner {

    private static Map<Map<UUID, ItemStack>, Integer> items = new HashMap<Map<UUID, ItemStack>, Integer>();

    public static void clear() {
        items.clear();
        for (World w : Bukkit.getWorlds()) {
            Iterator<String> iter = Settings.blacklistedworlds.iterator();
            while(iter.hasNext()) {
                String blacklistedWorld = iter.next();
                if(blacklistedWorld.equalsIgnoreCase(w.getName())) {
                    return;
                }
            }
            for(Entity e: w.getEntities()) {
                if (!(e instanceof Item)) continue;
                Item i2 = (Item) e;
                ItemStack is = i2.getItemStack();
                Material type = is.getType();
                if (type.name().contains("HELMET") || type.name().contains("CHESTPLATE") || type.name().contains("LEGGINGS") || type.name().contains("BOOTS") || type.name().contains("SWORD") || type.name().contains("PICKAXE") || type.name().contains("AXE") || type.name().contains("HOE") || type.name().contains("SPADE") || type.name().contains("BOW") || type.name().contains("SHOVEL")) {
                    int amount = is.getAmount();
                    int licznik = 0;
                    ItemStack is2 = is;
                    while (licznik != amount){
                        is2.setAmount(1);
                        Map<UUID, ItemStack> itemStackMap = new HashMap<>();
                        itemStackMap.put(i2.getUniqueId(), is2);
                        items.put(itemStackMap,1);
                        licznik++;
                    }
                    e.remove();
                }else{
                    int amount = is.getAmount();
                    is.setAmount(1);
                    Map<UUID, ItemStack> itemStackMap = new HashMap<>();
                    itemStackMap.put(i2.getUniqueId(), is);
                    if (items.containsKey(itemStackMap)) {
                        items.replace(itemStackMap, amount += items.get(is).intValue());
                    } else {
                        items.put(itemStackMap, amount);
                    }
                    e.remove();
                }
            }
        }
    }

    public static Map<Map<UUID, ItemStack>, Integer> getItemList() {
        return items;
    }
}
