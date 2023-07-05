package me.lemurxd;

import me.lemurxd.abyss.Abyss;
import me.lemurxd.abyss.Pages;
import me.lemurxd.utils.Settings;
import me.lemurxd.utils.VersionUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) ((Object) e.getWhoClicked());
        if (VersionUtil.is1_13_orOlder()) {
            if (e.getInventory().getTitle().equals(Settings.otchlanguiname) && e.getCurrentItem() != null && e.getCurrentItem().equals(Pages.getPageChanger())) {
                e.setCancelled(true);
                if (p.hasPermission("abyss.pages")) {
                    int page = Abyss.getMap().get(p);
                    if (e.isRightClick()) {
                        if (Abyss.getMap().get(p) != 0) {
                            p.openInventory(Abyss.getInv()[page - 1]);
                            Abyss.getMap().replace(p, Abyss.getMap().get(p) - 1);
                        } else {
                            p.sendMessage(Settings.otchlanlastpagedontexist);
                        }
                    } else if (Abyss.getInv()[page + 1] != null) {
                        p.openInventory(Abyss.getInv()[page + 1]);
                        Abyss.getMap().replace(p, Abyss.getMap().get(p) + 1);
                    } else {
                        p.sendMessage(Settings.otchlannextpagedontexist);
                    }
                } else {
                    p.sendMessage(Settings.permissionpages);
                }
            }
        } else {
            if (e.getView().getTitle().equals(Settings.otchlanguiname) && e.getCurrentItem() != null && e.getCurrentItem().equals(Pages.getPageChanger())) {
                e.setCancelled(true);
                if (p.hasPermission("abyss.pages")) {
                    int page = Abyss.getMap().get(p);
                    if (e.isRightClick()) {
                        if (Abyss.getMap().get(p) != 0) {
                            p.openInventory(Abyss.getInv()[page - 1]);
                            Abyss.getMap().replace(p, Abyss.getMap().get(p) - 1);
                        } else {
                            p.sendMessage(Settings.otchlanlastpagedontexist);
                        }
                    } else if (Abyss.getInv()[page + 1] != null) {
                        p.openInventory(Abyss.getInv()[page + 1]);
                        Abyss.getMap().replace(p, Abyss.getMap().get(p) + 1);
                    } else {
                        p.sendMessage(Settings.otchlannextpagedontexist);
                    }
                } else {
                    p.sendMessage(Settings.permissionpages);
                }
            }
        }
    }

    public void onclick(PlayerInteractEvent e) {
        e.getItem().getType().name();
    }


}