package me.ashesh.Event;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Module.Eco;
import me.ashesh.Module.Exp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MMDeath implements Listener {

    @EventHandler
    public void onDeathMythicmobs(MythicMobDeathEvent e) {
        LivingEntity kil = e.getKiller();
        if (kil instanceof Player) {
            Player p = (Player) kil;
            if (!p.hasMetadata("NPC")) {

                API.debugConsole("MOB: " + e.getMobType());

                // INIT
                String mobName = e.getMob().getName();
                String mobID = e.getMob().getMobType();
                Location mobLoc = e.getEntity().getLocation();
                MMOCoreEXP ins = MMOCoreEXP.getInstance();


                // TODO: Lanjutan
                if (ins.getConfig().getString("mob-list." + mobID) != null) {
                    API.debugConsole("MOB In List");

                    if (ins.getServer().getPluginManager().getPlugin("Vault") != null) {
                        if (ins.getConfig().getBoolean("module.money")) {
                            API.debugConsole("MOB Rewarded Money");
                            try {
                                new Eco().giveMoney(p, mobID, mobLoc, e.getMob());
                            } catch (Exception ex) {
                                Bukkit.getLogger().severe("[MMOCoreMoney] Money syntax not valid");
                            }
                        }
                    }

                    if (ins.getConfig().getBoolean("module.exp")) {
                        try {
                            API.debugConsole("MOB Rewarded EXP");
                            new Exp().giveExp(p, mobID, mobLoc, e.getMob());
                        } catch (Exception ex) {
                            Bukkit.getLogger().severe("[MMOCoreMoney] EXP syntax not valid");
                        }
                    }

                } else if (ins.getConfig().getBoolean("auto-add-mob-to-config")) {
                    API.debugConsole("MOB not in list adding mob to list");
                    Bukkit.getLogger().info("[MMOCoreExp] Found " + mobID + " but not registered in config. registered to config!");
                    API.addMobConf(mobID);
                }
            }
        }
    }
}
