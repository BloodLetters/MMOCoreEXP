package me.ashesh;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.api.util.MMOCoreUtils;
import net.Indyuce.mmocore.experience.EXPSource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class API {

    public static void debugConsole(String e) {
        if (MMOCoreEXP.getInstance().getConfig().getBoolean("debug")) {
            Bukkit.getLogger().info("[MMOCoreEXP-Debug] " + e);
        }
    }

    public static String chatColorized(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void spawnHolo(Location location, String msg) {
        MMOCoreUtils.displayIndicator(location, msg);
    }

    public static void giveMoney(Player p, float money, Location location) {
        float rest = (float)Math.round(money * 10.0F) / 10.0F;
        spawnHolo(location.add(0.0D, 0.7D, 0.0D), chatColorized("&e+" + rest + " Gold"));
    }

    public static void giveEXP(Player p, float xp, Location location) {
        float rest = (float)Math.round(xp * 10.0F) / 10.0F;
        // PlayerData.get(p).giveExperience(rest, EXPSource.OTHER);
        PlayerData.get(p).giveExperience(rest, EXPSource.OTHER, location.add(0.0D, 0.9D, 0.0D), true);
        //spawnHolo(location.add(0.0D, 0.9D, 0.0D), chatColorized("&e+" + rest + " EXP"));
    }

    public static void addMobConf(String name) {
        MMOCoreEXP main = MMOCoreEXP.getInstance();

        // EXP
        Map<String, Integer> exp = new HashMap();
        exp.put("Min", 1);
        exp.put("Max", 2);

        // Money
        Map<String, Integer> money = new HashMap();
        money.put("Min", 1);
        money.put("Max", 2);

        main.getConfig().set("mob-list." + name + ".EXP", exp);
        main.getConfig().set("mob-list." + name + ".Money", money);

        main.saveConfig();
        main.reloadConfig();
    }
}