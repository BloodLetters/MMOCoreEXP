package me.ashesh;

import me.ashesh.Command.command;
import me.ashesh.Command.completer;
import me.ashesh.Event.MMDeath;
import me.ashesh.Module.Eco;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMOCoreEXP extends JavaPlugin {

    private static MMOCoreEXP instance;
    private static Eco econ = null;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[MMOCoreEXP] Trying to enable plugin");

        this.getServer().getPluginManager().registerEvents(new MMDeath(), this);
        this.getCommand("MMOCoreEXP").setExecutor(new command());
        this.getCommand("MMOCoreEXP").setTabCompleter(new completer());

        if (this.getServer().getPluginManager().getPlugin("MMOCore") == null || this.getServer().getPluginManager().getPlugin("MythicLib") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] MMOCore or MythicLib not installed!. disabled plugin");
            getServer().getPluginManager().disablePlugin(this);
        }

        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().severe("[MMOCoreEXP] Vault not found!. Disabling MMOCoreExp Money drop module");
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
        RegisteredServiceProvider<Eco> rsp = getServer().getServicesManager().getRegistration(Eco.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public MMOCoreEXP() {
        instance = this;
    }

    public static Eco getEconomy() {
        return econ;
    }

    public static MMOCoreEXP getInstance() {
        return instance;
    }
}
