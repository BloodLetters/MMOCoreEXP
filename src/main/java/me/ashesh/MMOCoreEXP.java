package me.ashesh;

import me.ashesh.Event.MMDeath;
import me.ashesh.Module.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMOCoreEXP extends JavaPlugin {

    private static MMOCoreEXP instance;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[MMOCoreEXP] Trying to enable plugin");

        this.getServer().getPluginManager().registerEvents(new MMDeath(), this);
        if (this.getServer().getPluginManager().getPlugin("MMOCore") == null || this.getServer().getPluginManager().getPlugin("MythicLib") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] MMOCore or MythicLib not installed!. disabled plugin");
            getServer().getPluginManager().disablePlugin(this);
        }

        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] Vault not found!. Disabling MMOCoreExp Money drop module");
        } else {
            this.getServer().getPluginManager().registerEvents(new Economy(), this);
        }

        Bukkit.getLogger().info("[MMOCoreEXP] Plugin success to enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public MMOCoreEXP() {
        instance = this;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static MMOCoreEXP getInstance() {
        return instance;
    }
}
