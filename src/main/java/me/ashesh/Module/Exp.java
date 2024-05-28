package me.ashesh.Module;

import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Utils.calculator;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Exp {
    public void giveExp(Player p, String mobID, Location location) {
        MMOCoreEXP ins = MMOCoreEXP.getInstance();

        int getMin = ins.getConfig().getInt("mob-list." + mobID + ".Exp.Min");
        int getMax = ins.getConfig().getInt("mob-list." + mobID + ".Exp.Max");

        // formula
        String formula = ins.getConfig().getString("formula.exp");
        String replaced = formula.replace("{min}", String.valueOf(getMin).replace("{max}", String.valueOf(getMax)));
        PlaceholderAPI.setPlaceholders(p, replaced);

        // calc
        double res = calculator.evaluate(replaced);
        API.giveEXP(p, (float) res, location);
    }
}
