package me.dreamdevs.abyss;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cleaner {
    private static Map<Map<UUID, ItemStack>, Integer> itemy = new HashMap<Map<UUID, ItemStack>, Integer>();

    public static void clear() {
        itemy.clear();
        for (World w : Bukkit.getWorlds()) {
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
                        itemy.put(itemStackMap,1);
                        licznik++;
                    }
                    e.remove();
                }else{
                    int amount = is.getAmount();
                    is.setAmount(1);
                    Map<UUID, ItemStack> itemStackMap = new HashMap<>();
                    itemStackMap.put(i2.getUniqueId(), is);
                    if (itemy.containsKey(itemStackMap)) {
                        itemy.replace(itemStackMap, amount += itemy.get(is).intValue());
                    } else {
                        itemy.put(itemStackMap, amount);
                    }
                    e.remove();
                }
            }
        }
    }

    public static HashMap<Map<UUID, ItemStack>, Integer> getItemList() {
        return new HashMap<Map<UUID, ItemStack>, Integer>(itemy);
    }
}
