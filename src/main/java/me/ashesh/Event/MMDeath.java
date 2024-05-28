package me.ashesh.Event;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Module.Eco;
import me.ashesh.Module.Exp;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.api.util.MMOCoreUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MMDeath implements Listener {

    public void onDeathMythicmobs(MythicMobDeathEvent e) {
        LivingEntity kil = e.getKiller();
        if (kil instanceof Player) {
            Player p = (Player) kil;
            if (!p.hasMetadata("NPC")) {

                // INIT
                String mobName = e.getMob().getName();
                String mobID = e.getMob().getMobType();
                Location mobLoc = e.getEntity().getLocation();
                MMOCoreEXP ins = MMOCoreEXP.getInstance();


                // TODO: Lanjutan
                if (ins.getConfig().getString("mob-list." + mobID) == null) {
                    if (ins.getConfig().getBoolean("module.money")) {
                        new Eco().giveMoney(p, mobID, mobLoc, e.getMob());
                    }

                    if (ins.getConfig().getBoolean("module.exp")) {
                        new Exp().giveExp(p, mobID, mobLoc, e.getMob());
                    }

                } else if (ins.getConfig().getBoolean("auto-add-mob-to-config")) {
                    Bukkit.getLogger().info("[MMOCoreExp] Found " + mobID + " but not registered in config. registered to config!");
                    API.addMobConf(mobID);
                }
            }
        }
    }
}
