package me.dreamdevs;

import me.dreamdevs.abyss.Abyss;
import me.dreamdevs.abyss.Pages;
import me.dreamdevs.utils.Settings;
import me.dreamdevs.utils.VersionUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    public static long warntime;
    public static long warntimeclose;

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        new Settings(this);
        new VersionUtil();
        Pages.prepareItemLores();
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        this.getCommand("otchlan").setExecutor(new Cmds());
        if (Settings.rows < 2) {
            Settings.rows = 2;
        }
        if (Settings.rows > 6) {
            Settings.rows = 6;
        }
        Abyss.setRows(Settings.rows);
        warntime = System.currentTimeMillis()/1000+ Settings.timer;
        warntimeclose = System.currentTimeMillis()/1000+ Settings.timerend+ Settings.timer;
        this.abysstask();
        this.warntask();
    }


    public static Main getInstance() {
        return instance;
    }

    private void warntask() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Integer current = Math.toIntExact(System.currentTimeMillis() / 1000);
            for(Integer i : Settings.warnslist.keySet()) {
                if(i.intValue() == ((warntime-current))) {
                    Bukkit.broadcastMessage(""+ Settings.warnslist.get(i));
                }
            }
            for(Integer i : Settings.warnscloselist.keySet()) {
                if(i.intValue() == ((warntimeclose-current))) {
                    Bukkit.broadcastMessage(""+ Settings.warnscloselist.get(i));
                }
            }
        }, 20, 20);
    }

    private void abysstask() {
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                Abyss.open();
                abyssclosetask();
            }
        }, Settings.timer*20);
    }

    private void abyssclosetask() {
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getOpenInventory() != null && all.getOpenInventory().getTitle().equals(Settings.otchlanguiname)) {
                        all.closeInventory();
                    }
                }
                Pages.reset();
                Abyss.getInv()[0].clear();
                Abyss.getInv()[0] = null;
                abysstask();
                warntime = System.currentTimeMillis()/1000+ Settings.timer;
                warntimeclose = System.currentTimeMillis()/1000+ Settings.timerend+ Settings.timer;
                Bukkit.broadcastMessage(Settings.otchlanclearbroadcast);
            }
        }, Settings.timerend*20);
    }

}