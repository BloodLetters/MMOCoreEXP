package me.ashesh.Module;

import io.lumine.mythic.lib.hologram.Hologram;
import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Utils.calculator;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Eco {
    public void giveMoney(Player p, String mobID, Location location) {
        MMOCoreEXP ins = MMOCoreEXP.getInstance();
        Economy money = (Economy) MMOCoreEXP.getEconomy();

        int getMin = ins.getConfig().getInt("mob-list." + mobID + ".Money.Min");
        int getMax = ins.getConfig().getInt("mob-list." + mobID + ".Money.Max");

        // formula
        String formula = ins.getConfig().getString("formula.money");
        String replaced = formula.replace("{min}", String.valueOf(getMin).replace("{max}", String.valueOf(getMax)));
        PlaceholderAPI.setPlaceholders(p, replaced);

        // calc
        double res = calculator.evaluate(replaced);
        money.depositPlayer(p, res);
        API.giveMoney(p, (float) res, location);
    }
}
