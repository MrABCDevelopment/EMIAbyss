package me.lemurxd.abyss;

import me.lemurxd.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Pages {
    private static ItemStack changer = new ItemStack(Material.NETHER_STAR, 1);
    private static int page = 0;
    private static int slot = 0;

    public static void setSlot(ItemStack is, int amount) {
        ItemStack is2 = is.clone();
        if (amount > 64) {
            do {
                if (slot < (Abyss.getRows() - 1) * 9) {
                    is2.setAmount(64);
                    Abyss.getInv()[page].setItem(slot, is2);
                    for(Map.Entry<Map<UUID, ItemStack>, Integer> mapEntry : Cleaner.getItemList().entrySet()) {
                        Cleaner.getItemList().replace(mapEntry.getKey(), amount -= 64);
                    }
                    //Cleaner.getItemList().replace(is, amount -= 64);
                    ++slot;
                    continue;
                }
                Pages.createNewPage();
            } while (amount > 0);
        } else if (slot < (Abyss.getRows() - 1) * 9) {
            Cleaner.getItemList().remove(is);
            is.setAmount(amount);
            Abyss.getInv()[page].setItem(slot, is);
            ++slot;
        } else {
            Pages.createNewPage();
        }
    }

    private static void createNewPage() {
        System.out.println("Tworze nowa strone...");
        Abyss.getInv()[++Pages.page] = Bukkit.createInventory(null, Abyss.getRows() * 9, Settings.otchlanguiname);
        slot = 0;
        if (changer == null) {
            Pages.prepareItemLores();
        }
        Abyss.getInv()[page - 1].setItem(Abyss.getRows() * 9 - 1, changer);
        Abyss.getInv()[page].setItem(Abyss.getRows() * 9 - 1, changer);
    }

    public static void prepareItemLores() {
        ItemMeta im = changer.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§bKliknij §aLPM§b aby przejsc na kolejna strone.");
        lore.add("§bKliknij §aPPM§b aby przejsc na poprzednia strone.");
        im.setDisplayName("§eZmien strone");
        im.setLore(lore);
        changer.setItemMeta(im);
    }

    public static ItemStack getPageChanger() {
        return changer;
    }

    public static int getPage() {
        return page;
    }

    public static void reset() {
        page = 0;
        slot = 0;
    }
}