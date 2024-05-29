package me.ashesh.Module;

import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.lib.hologram.Hologram;
import me.ashesh.API;
import me.ashesh.MMOCoreEXP;
import me.ashesh.Utils.calculator;
import me.clip.placeholderapi.PlaceholderAPI;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Eco {
    public void giveMoney(Player p, String mobID, Location location, ActiveMob mob) throws Exception {
        MMOCoreEXP ins = MMOCoreEXP.getInstance();
        Economy money = (Economy) MMOCoreEXP.getEconomy();

        int getMin = ins.getConfig().getInt("mob-list." + mobID + ".Money.Min");
        int getMax = ins.getConfig().getInt("mob-list." + mobID + ".Money.Max");

        int p_lvl = PlayerData.get(p).getLevel();
        double p_exp = PlayerData.get(p).getExperience();

        // formula
        String formula = ins.getConfig().getString("formula.money");
        String replaced = formula.replace("{min}", String.valueOf(getMin))
                .replace("{max}", String.valueOf(getMax))
                .replace("{p_lvl}", String.valueOf(p_lvl))
                .replace("{m_lvl}", String.valueOf(mob.getLevel()));

        PlaceholderAPI.setPlaceholders(p, replaced);

        // calc
        double res = calculator.evaluateExpression(replaced);
        money.depositPlayer(p, res);
        API.giveMoney(p, (float) res, location);
    }
}
