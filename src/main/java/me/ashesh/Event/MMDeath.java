package me.ashesh.Event;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.event.Listener;

public class MMDeath implements Listener {

    public void onDeathMythicmobs(MythicMobDeathEvent e) {
        // INIT
        String mobName = e.getMob().getName();
        double mobLevel = e.getMobLevel();
        String mobID = e.getMob().getMobType();

        // TODO: Lanjutan
    }
}
