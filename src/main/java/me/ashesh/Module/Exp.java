package me.ashesh.Module;

import io.lumine.mythic.core.mobs.ActiveMob;
import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Utils.calculator;
import me.clip.placeholderapi.PlaceholderAPI;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Exp {
    public void giveExp(Player p, String mobID, Location location, ActiveMob mob) throws Exception {
        MMOCoreEXP ins = MMOCoreEXP.getInstance();

        int getMin = ins.getConfig().getInt("mob-list." + mobID + ".EXP.Min");
        int getMax = ins.getConfig().getInt("mob-list." + mobID + ".EXP.Max");

        int p_lvl = PlayerData.get(p).getLevel();

        // formula
        String formula = ins.getConfig().getString("formula.exp");
        API.debugConsole("EXP: " + mob.getMobType());

        String replaced = formula.replace("{min}", String.valueOf(getMin))
                .replace("{max}", String.valueOf(getMax))
                        .replace("{p_lvl}", String.valueOf(p_lvl))
                        .replace("{m_lvl}", String.valueOf(mob.getLevel()));

        API.debugConsole("EXP Format: " + replaced);
        PlaceholderAPI.setPlaceholders(p, replaced);

        // calc
        double res = calculator.evaluateExpression(replaced);
        API.giveEXP(p, (float) res, location);
    }
}
